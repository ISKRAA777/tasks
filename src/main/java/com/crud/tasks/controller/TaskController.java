package com.crud.tasks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.tasks.domain.TaskDto;

import java.util.ArrayList;
import java.util.List;
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
    @RequestMapping(method = RequestMethod.GET,value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(Long taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edit test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void CreateTask(TaskDto taskDto) {
    }
}
