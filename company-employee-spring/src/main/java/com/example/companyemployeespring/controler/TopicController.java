package com.example.companyemployeespring.controler;


import com.example.companyemployeespring.entity.Comment;
import com.example.companyemployeespring.entity.Topic;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.CommentService;
import com.example.companyemployeespring.service.EmployeeService;
import com.example.companyemployeespring.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final CommentService commentService;

    @PostMapping("/e/addtopics")
    public String addTopic(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String text) {
        Topic topic = new Topic();
        topic.setEmployee(currentUser.getEmployee());
        topic.setText(text);
        topic.setCreatedDate(LocalDate.now());
        topicService.save(topic);
        return "redirect:/e/topics";
    }

    @GetMapping("/e/topics")
    public String tpics(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Topic> allTopics = topicService.findByEmployee_Company_Id(currentUser.getEmployee().getCompany().getId());
        modelMap.addAttribute("currentuser",currentUser.getEmployee());
        modelMap.addAttribute("allTopics", allTopics);

        return "topics";
    }

    @GetMapping("/e/addtopics")
    public String add() {
        return "addTopic";
    }


  @GetMapping("/e/delete")
    public String deleteTopic(@AuthenticationPrincipal CurrentUser currentUser,@RequestParam int id){
        topicService.deleteById(id,currentUser.getEmployee().getId());
        return "redirect:/e/topics";
  }

}
