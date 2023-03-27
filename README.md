User API
The User API is used for registering, logging in and logging out users.

1.Register a user
Request
POST /user/register

{
    "username": "exampleuser",
    "password": "examplepassword",
    "email": "exampleuser@example.com"
}
Response
201 Created: User registered successfully
409 Conflict: User already exists

{
    "id": 1,
    "username": "exampleuser",
    "email": "exampleuser@example.com",
    "tasks": []
}

2.Login a user
Request
POST /user/login

{
    "username": "exampleuser",
    "password": "examplepassword"
}
Response
200 OK: User logged in successfully
401 Unauthorized: Invalid credentials

{
    "id": 1,
    "username": "exampleuser",
    "email": "exampleuser@example.com",
    "tasks": []
}

3.Logout a user
Request
GET /user/logout

Response
200 OK: User logged out successfully
"Successfully logged out"


Task Controller
The TaskController handles requests related to tasks. It exposes endpoints to create, update, delete, and retrieve tasks.

Endpoints
Get all tasks
GET /tasks/task/all/{id}

Retrieves all the tasks for the user with the specified id.

Save task
POST /tasks/save/task/{id}

Saves a new task to the user with the specified id.

Update task
PUT /tasks/update/{id}

Updates the task with the specified id.

Delete task
DELETE /tasks/tasks/delete

Deletes the task with the specified id.

Get tasks sorted by completion status
GET /tasks/sortedByNotCompletedCompilation/all/{id}

Retrieves all tasks for the user with the specified id that are not completed.

GET /tasks/sortedByCompletedCompilation/all/{id}

Retrieves all tasks for the user with the specified id that are completed.

Request and Response Formats
Request Formats
Save task
{
    "title": "Title",
    "description": "Description",
    "completionStatus": false
}
Update task

{
    "description": "Description",
    "completionStatus": false
}
Delete task

{
    "id": 1
}
Response Formats
Get all tasks
{

    "firstName": "First",
    "lastName": "Last",
    "email": "email@example.com",
    "tasks": [
        {

            "description": "Description",
            "completionStatus": false
        },
        {

            "description": "Description",
            "completionStatus": true
        }
    ]
}
Save task, Update task, Get tasks sorted by completion status, Get tasks sorted by not completed status

{
    "firstName": "First",
    "lastName": "Last",
    "email": "email@example.com",
    "tasks": [
        {

            "description": "Description",
            "completionStatus": false
        },
        {

            "description": "Description",
            "completionStatus": true
        }
    ]
}
Delete task
{
    "firstName": "First",
    "lastName": "Last",
    "email": "email@example.com",
    "tasks": [
        {
            "description": "Description",
            "completionStatus": true
        }
    ]
}
