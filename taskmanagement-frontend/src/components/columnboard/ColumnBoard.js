import React from "react";
import TaskCard from "../taskcard/TaskCard";
import "./ColumnBoard.css";

function Column({ title, tasks }) {
    return (
        <div className="column">
            <h2>{title}</h2>
            {tasks.length > 0 ? (
                tasks.map((task) => <TaskCard key={task.id} task={task} />)
            ) : (
                <p>No Tasks.</p>
            )}
        </div>
    );
}

export default Column;
