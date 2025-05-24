CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL
);


CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    priority INT,
    deadline DATE,
    status VARCHAR(20),
    user_id INT REFERENCES users(id)
);

CREATE TABLE tags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);

CREATE TABLE task_tags (
    task_id INT REFERENCES tasks(id),
    tag_id INT REFERENCES tags(id),
    PRIMARY KEY(task_id, tag_id)
);

CREATE TABLE task_history (
    id SERIAL PRIMARY KEY,
    task_id INT REFERENCES tasks(id),
    change_description TEXT,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task_dependencies (
    task_id INT REFERENCES tasks(id),
    depends_on_task_id INT REFERENCES tasks(id),
    PRIMARY KEY (task_id, depends_on_task_id)
);
