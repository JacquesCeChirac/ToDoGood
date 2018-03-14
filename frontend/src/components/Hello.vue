<template>
  <div class="hello">
    <img src="./../assets/task_scheduling.png">
    <h1>{{ msg }}</h1>
    <div class="form-row">
      <div class="col">
        <input class="b-form-input col-md-3" v-model="newTodo.description" placeholder="Ajoutez une tâche">
        <input class="b-form-input col-md-3" v-model="newTodo.author" placeholder="Qui doit la faire ?" :disabled="!newTodo.description">
        <input class="b-form-input col-md-3" @click="addTask(newTodo)" v-model="newTodo.duration" placeholder="Temps en minutes" :disabled="!newTodo.author" :class="{'input-invalid' : errors.label.duration}">
        <button class="btn-primary" @click="addTask(newTodo)">Créer</button>
      </div>
    </div>
    <div v-if="todos[idx]" >
      <b-modal v-model="show" title="Modifier une tâche" >
        <b-container fluid>
          <b-row class="mb-1 text-center">
            <b-col cols="3"> </b-col>
            <b-col>Valeurs actuelles</b-col>
          </b-row>
          <b-row class="mb-3">
            <b-col cols="4">Description</b-col>
            <b-col><b-form-input v-model="todos[idx].description" disabled/></b-col>
          </b-row>
        <b-row class="mb-3">
            <b-col cols="4">Auteur</b-col>
            <b-col><b-form-input v-model="todos[idx].author" disabled/></b-col>
          </b-row>
        <b-row class="mb-3">
            <b-col cols="4">Temps</b-col>
            <b-col><b-form-input v-model="todos[idx].duration" disabled/></b-col>
          </b-row>
          <b-row class="mb-1 text-center">
            <b-col cols="3"> </b-col>
            <b-col>Nouvelles valeurs</b-col>
          </b-row>
          <b-row class="mb-3">
            <b-col cols="4">Description</b-col>
            <b-col><b-form-input v-model="newTodo.description"/></b-col>
          </b-row>
          <b-row class="mb-3">
            <b-col cols="4">Auteur</b-col>
            <b-col><b-form-input v-model="newTodo.author"/></b-col>
          </b-row>
          <b-row class="mb-3">
            <b-col cols="4">Temps</b-col>
            <b-col><b-form-input v-model="newTodo.duration"/></b-col>
          </b-row>
        </b-container>
        <div slot="modal-footer" class="w-100">
          <b-btn class="float-right" @click="show=false">Annuler</b-btn>&nbsp;&nbsp;&nbsp;
          <b-btn class="float-right" @click="editTask(newTodo, todos[idx].id)">Valider</b-btn>
        </div>
      </b-modal>
    </div>
    <div>
      <b-modal v-model="modalShow">
        Hello From Modal!
      </b-modal>
    </div>
    <hr style="width: 650px">
    <h2>Vos tâches : </h2>
    <ul class="container" style="text-align: left">
      <li v-for="(todo, index) in todos" :key="index" class="row">
        <span class="col-sm-8">
          <input type="checkbox" v-model="todo.done" v-on:change="markTask(todo.id)"/>
          <span :class="{'marked' : todo.done}"><b>{{todo.description}}</b> de <i>{{todo.author}}</i> reste {{todo.duration}} minutes</span>
        </span>
        <span class="col-sm-4 text-center">
          <button class="btn btn-success" v-on:click="markTask(todo.id)" v-if="!todo.done">Valider</button>
          <button class="btn btn-warning" v-on:click="markTask(todo.id)" v-if="todo.done">Refaire</button>
          <button class="btn btn-info" v-if="!todo.done" @click="showModal(todo, index)">Modifier</button>
          <button class="btn btn-danger" aria-label="Close" v-if="todo.done">
            <span aria-hidden="true" @click="deleteTask(todo.id)">Supprimer</span>
          </button>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
  import { Services } from '../Services'
  export default {
    name: 'hello',
    data () {
      return {
        msg: 'ToDoGood',
        modalShow: false,
        show: false,
        checkedNames: [],
        task: '',
        isDone: true,
        checked: '',
        author: '',
        todos: [],
        newTodo: {
          task: '',
          author: '',
          duration: ''
        },
        idx: null,
        errors: {
          isError: false,
          label: {
            task: false,
            author: false,
            duration: false
          }

        }
      }
    },
    created () {
      Services.getTasks().then(resp => {
        this.todos = resp.data
      })
    },
    methods: {
      addTask (newTodo) {
        Services.addTask(newTodo).then(resp => {
          this.todos = resp
        })
      },
      markTask (id) {
        Services.markTask(id).then(resp => {
          this.todos = resp
        })
      },
      deleteTask (id) {
        Services.deleteTask(id).then(resp => {
          this.todos = resp
        })
      },
      checkForm (newTodo) {
        if (isNaN(newTodo.duration)) {
          this.errors.label.duration = true
          this.errors.isError = true
        }
        if (newTodo.author === 'Tedje') {
          this.errors.label.author = true
          this.errors.isError = true
        }
        return this.errors.isError
      },
      showModal (todo, index) {
        this.idx = index
        this.show = true
      },
      editTask (newTodo, id) {
        Services.editTask(newTodo, id).then(resp => {
          this.todos = resp
        })
        this.show = false
      }
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  img{
    height: 250px;
  }
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 15px 10px;
  }
  a {
    color: #42b983;
  }
  .marked{
    opacity: 0.5;
    text-decoration: line-through;
  }
  .error{
    background-color: red;
  }
</style>
