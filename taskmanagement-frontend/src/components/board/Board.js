import React, { useEffect, useState } from "react";
import ColumnBoard from "../columnboard/ColumnBoard";
import axios from "axios";
import "./Board.css";
import {DragDropContext, Droppable, Draggable} from "react-beautiful-dnd";

function Board() {
    const [tasks, setTasks] = useState([]);


    useEffect(() => {
        axios
            .get("http://localhost:8080/api/tasks")
            .then((response) => setTasks(response.data))
            .catch((error) =>  console.error("There was an error fetching the tasks!", error));
    }, []);


    const columns = {
        TO_DO:{
            name: "To Do",
            color: "#f8d7da",
            tasks: tasks.filter((task) => task.status === "TO_DO"),
        },

        IN_PROGRESS:{
            name: "In Progress",
            color: "#ffeeba",
            tasks: tasks.filter((task) => task.status === "IN_PROGRESS"),
        },

        IN_REVIEW: {
            name: "In Review",
            color: "#d1ecf1",
            tasks: tasks.filter((task) => task.status === "IN_REVIEW"),
        },

        DONE: {
            name: "Done",
            color: "#d4edda",
            tasks: tasks.filter((task) => task.status === "DONE"),
        },
    };

    //ici je gere le drag and drop
    const onDragEnd = (result) => {
        const {source, destination } = result;

        if (!destination) return; // si l'elelement n'est pas deposé dans une colonne valide

        //je triouve la tache deplacée et son nouveau statut
        //const movedTask = columns[source.droppableId].tasks[source.index];
        const movedTask = tasks.find((task) => task.id === result.draggableId);
        const newStatus = destination.droppableId;

        //je mets a jour le status de la tache
        if (movedTask.status !== newStatus){
            movedTask.status = newStatus;

            //j'envois la mise a jour au backend
            axios
                .patch(`http://localhost:8080/api/tasks/${movedTask.id}/status`, {
                    status: newStatus })
                .then((response) => {
                    //je mets a jour l'etat local apres confirmation du backend
                    setTasks((prevTasks) =>
                        prevTasks.map((task) =>
                            task.id === movedTask.id ? { ...task, status: newStatus} : task
                        )
                    );
                })
                .catch((error) => console.error("erreur lors de la mise a jour:", error));

        }


    };

    return (
        <div className="board">
            <h1>Kanban Board</h1>
            <DragDropContext onDragEnd={onDragEnd}>
                <div className="kanban-columns">
                    {Object.entries(columns).map(([status, column]) => (
                        <Droppable key={status} droppableId={status}>
                            {(provided) => (
                                <div
                                    className="kanban-column"
                                    ref={provided.innerRef}
                                    {...provided.droppableProps}
                                    style={{ background: column.color}}
                                >
                                    <h2>{column.name}</h2>
                                    {column.tasks.map((task, index) => (
                                        <Draggable
                                            key={task.id}
                                            draggableId={String(task.id)}
                                            index={index}
                                        >
                                            {(provided) => (
                                                  <div
                                                      className="kanban-task-card"
                                                      ref={provided.innerRef}
                                                      {...provided.draggableProps}
                                                      {...provided.dragHandleProps}
                                                  >
                                                      <h4>{task.title}</h4>
                                                      <p>{task.description}</p>
                                                  </div>
                                            )}
                                        </Draggable>
                                    ))}
                                    {provided.placeholder}
                                </div>
                            )}
                        </Droppable>
                    ))}
                </div>
            </DragDropContext>
        </div>
    );
}

export default Board;