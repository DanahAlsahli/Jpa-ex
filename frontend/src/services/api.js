import axios from 'axios';

// Base URL للـ Backend
const API_BASE_URL = 'http://localhost:8080/api';

// إنشاء instance من axios
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// ============================================
// Teacher Endpoints
// ============================================

// 1. Get all teachers
export const getAllTeachers = () => api.get('/teachers');

// 2. Get teacher by id (with all details)
export const getTeacherById = (id) => api.get(`/teachers/${id}`);

// 3. Add new teacher
export const addTeacher = (teacher) => api.post('/teachers', teacher);

// 4. Update teacher
export const updateTeacher = (id, teacher) => api.put(`/teachers/${id}`, teacher);

// 5. Delete teacher
export const deleteTeacher = (id) => api.delete(`/teachers/${id}`);

// 6. Add teacher address
export const addTeacherAddress = (id, address) =>
  api.post(`/teachers/${id}/address`, address);

// 7. Update teacher address
export const updateTeacherAddress = (id, address) =>
  api.put(`/teachers/${id}/address`, address);

// 8. Delete teacher address
export const deleteTeacherAddress = (id) =>
  api.delete(`/teachers/${id}/address`);

export default api;