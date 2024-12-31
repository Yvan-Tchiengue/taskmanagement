import React from "react";
import "./TaskCard.css";

function TaskCard({ task }) {
    return (
        <div className="task-card">
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <span className={`status status-${task.status.toLowerCase()}`}>
                {task.status}
            </span>
        </div>
    );
}

export default TaskCard;
