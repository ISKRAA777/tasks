package com.crud.tasks.controller;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crud.tasks.domain.TaskDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//Zazwyczaj REST API są udostępniane pod adresami typu /api/method — właśnie
// to chcemy osiągnąć.
//W tym celu musimy określić adres w naszej aplikacji. Aby tego dokonać, wewnątrz
// okrągłych nawiasów wpisujemy ścieżkę /v1/task, gdzie v1 oznacza wersję
// naszego API (wykonujemy je pierwszy raz, dlatego v1 jest najlepszym
// rozwiązaniem). Dzięki tej operacji, nasz RestController będzie
// dostępny pod poniższym adresem: http://localhost:8080/v1/task
@RestController
@RequestMapping("v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(Long taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edit test title", "Test content");
    }

    //było :@RequestMapping(method = RequestMethod.POST, value = "createTask")
    //    public void CreateTask(TaskDto taskDto) {
    //jest po zmianie zgodnie z Kolejną nową rzeczą jest adnotacja @RequestBody, która
    // pojawiła się w ciele metody createTask() - informuje ona Springa o tym, że powinien
    // szukać obiektu (a dokładnie jego reprezentacji w formacie JSON, co określiliśmy za
    // pomocą consumes) w ciele (body) żądania.

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
