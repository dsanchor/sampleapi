
## Endpoints

### 1. Create a New TODO Item

**Endpoint:**  
`POST /todos`

**Description:**  
Creates a new TODO item.  
- The request body must include only the mandatory fields: `name` and `details`.  
- The `creationDate`, `closeDate`, and `done` flag are set automatically.

**Request Example:**

```bash
curl -X POST http://localhost:8080/todos \
     -H "Content-Type: application/json" \
     -d '{"name": "Comprar comestibles", "details": "Leche, pan, huevos"}'
```

**Response:**  
- Returns the created TODO item with all fields populated (including auto-generated ones).  
- HTTP status code: 200 OK

---

### 2. Mark a TODO Item as Done

**Endpoint:**  
`PUT /todos/{id}/done`

**Description:**  
Marks an existing TODO item as done.  
- The operation sets the `done` flag to `true` and automatically assigns the `closeDate`.

**Request Example:**

```bash
curl -X PUT http://localhost:8080/todos/1/done
```

**Response:**  
- Returns the updated TODO item.  
- HTTP status code: 200 OK if the item exists; 404 Not Found otherwise.

---

### 3. Retrieve All TODO Items

**Endpoint:**  
`GET /todos`

**Description:**  
Retrieves a list of all TODO items.

**Request Example:**

```bash
curl -X GET http://localhost:8080/todos
```

**Response:**  
- Returns an array of TODO items.  
- HTTP status code: 200 OK

---

### 4. Retrieve a Specific TODO Item

**Endpoint:**  
`GET /todos/{id}`

**Description:**  
Retrieves the details of a specific TODO item by its id.

**Request Example:**

```bash
curl -X GET http://localhost:8080/todos/1
```

**Response:**  
- Returns the TODO item if available.  
- HTTP status code: 200 OK if found; 404 Not Found otherwise.

---

### 5. Delete a TODO Item

**Endpoint:**  
`DELETE /todos/{id}`

**Description:**  
Deletes a specific TODO item by its id.

**Request Example:**

```bash
curl -X DELETE http://localhost:8080/todos/1
```

**Response:**  
- HTTP status code: 200 OK when deletion is successful; 404 Not Found if the item does not exist.