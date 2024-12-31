import React, { useEffect, useState } from "react";
import axios from "axios";

function ProjectList() {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        // Fetch projects with tasks from the backend
        axios
            .get("http://localhost:8080/api/projects")
            .then(response => setProjects(response.data))
            .catch(error =>
                console.error("There was an error fetching the projects!", error)
            );
    }, []);

    return (
        <div>
            <h2>Liste des projets</h2>
            <ul>
                {projects.map((project) => (
                    <li key={project.id}>
                        <h3>{project.name}</h3>
                        <p>{project.description}</p>
                        <h4>Taches associées:</h4>
                        {project.tasks && project.tasks.length > 0? (
                            <ul>
                                {project.tasks.map((task) => (
                                    <li key={task.id}>
                                        <strong>{task.title}</strong>: {task.description} -{" "}
                                        <em>{task.status}</em>
                                    </li>
                                ))}
                            </ul>
                        ): (
                            <p>Aucune Tache associée.</p>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ProjectList;
