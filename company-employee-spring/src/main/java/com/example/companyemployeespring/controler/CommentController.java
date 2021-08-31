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
public class CommentController {

    private final CommentService commentService;
    private final TopicService topicService;

    @PostMapping("/e/addcomment")
    public String addTopic(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String text, @RequestParam(name = "topicId") Topic id) {
        Comment comment=new Comment();
        comment.setEmployee(currentUser.getEmployee());
        comment.setText(text);
        comment.setCreatedDate(LocalDate.now());
        comment.setTopic(id);
        commentService.save(comment);
        return "redirect:/e/comments?id="+id.getId();
    }

//    @GetMapping("/e/topics")
//    public String tpics(ModelMap modelMap){
//        List<Topic> allTopics = topicService.findAll();
//        modelMap.addAttribute("allTopics",allTopics);
//        return "topics";
//    }

    @GetMapping("/e/comments")
    public String comments(ModelMap modelMap,@RequestParam Topic id){
        Topic byId = topicService.getById(id.getId());
        modelMap.addAttribute("topic",byId);
        List<Comment> byTopicId = commentService.findByTopicId(id.getId());
        modelMap.addAttribute("comments",byTopicId);
        return "comments";
    }

    @GetMapping("/e/addComments")
    public String add(ModelMap modelMap, @RequestParam int id){
        modelMap.addAttribute("topicId", id);

        return "addComment";
    }





//    @GetMapping("/delete")
//    public String deleteCompany(@RequestParam int id) {
//        messageService.deleteById(id);
//        return "redirect:/messages";
//    }

}
