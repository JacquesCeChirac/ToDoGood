import Vue from 'vue'
import { HTTP } from './main'

export const Services = new Vue({
  data () {
    return {
    }
  },
  methods: {
    getTask () {
      return HTTP.get('todos').then(resp => {
        return resp
      })
    },
    getTasks () {
      return HTTP.get('/api/tasks').then(resp => {
        return resp
      })
    },
    addTask (newTodo = {}) {
      return HTTP.post('/api/addTask', newTodo).then(resp => {
        return this.getTasks()
      }).then(resp => {
        return resp.data
      })
    },
    markTask (id) {
      return HTTP.post('/api/markTask/' + id).then(resp => {
        return this.getTasks()
      }).then(resp => {
        return resp.data
      })
    },
    deleteTask (id) {
      return HTTP.post('/api/deleteTask/' + id).then(resp => {
        return this.getTasks()
      }).then(resp => {
        return resp.data
      })
    },
    editTask (newTodo = {}, id) {
      return HTTP.patch('/api/editTask/' + id, newTodo).then(resp => {
        return this.getTasks()
      }).then(resp => {
        return resp.data
      })
    }
  }
})
