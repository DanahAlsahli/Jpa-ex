import React, { useEffect, useState } from 'react'
import { getAllTeachers } from './services/api'

function App() {
  const [teachers, setTeachers] = useState([])
  const [error, setError] = useState(null)

  useEffect(() => {
    getAllTeachers()
      .then((response) => {
        setTeachers(response.data)
      })
      .catch((err) => {
        setError(err.message)
      })
  }, [])

  return (
    <div style={{ padding: '20px' }}>
      <h1>School Management System</h1>
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      <h2>Teachers ({teachers.length})</h2>
      <pre>{JSON.stringify(teachers, null, 2)}</pre>
    </div>
  )
}

export default App