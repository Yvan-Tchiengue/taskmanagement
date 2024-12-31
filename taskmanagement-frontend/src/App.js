import './App.css';

import React from "react";
import Board from "./components/board/Board";
import ProjectList from "./components/projectList";

function App() {
  return (
    <div className="App">
    <h1>Tasks Management</h1>
      <Board />
    </div>
  );
}

export default App;
