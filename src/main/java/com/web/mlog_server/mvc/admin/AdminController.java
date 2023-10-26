package com.web.mlog_server.mvc.admin;

import com.web.mlog_server.mvc.post.PostDto;
import com.web.mlog_server.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
@Secured("ADMIN")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody AdminDto.LoginDto dto) {
        return adminService.login(dto.getId(), dto.getPassword());
    }

    @PostMapping("/auth")
    public boolean isAuth() {
        return true;
    }

    @GetMapping("/postList")
    public List<AdminDto.TableDto> getAllPosts() {
        return adminService.getAllPosts();
    }
    @GetMapping("/projectList")
    public List<AdminDto.TableDto> getAllProjects() {
        return adminService.getAllProjects();
    }
    @GetMapping("/seriesList")
    public List<PostDto.SeriesCommonDto> getSeriesList() {
        return adminService.getSeriesList();
    }
    @PostMapping("/series")
    public Boolean addSeries(@RequestBody PostDto.SeriesCommonDto dto) {
        return adminService.addSeries(dto.getSeries());
    }
    @DeleteMapping("/series/{series}")
    public Boolean deleteSeries(@PathVariable String series) {
        return adminService.deleteSeries(series);
    }
    @PutMapping("/series")
    public Boolean changeSeries(@RequestBody PostDto.SeriesChangeDto dto) {
        return adminService.changeSeries(dto);
    }
    @GetMapping("/post/{id}")
    public PostDto.DetailDto getPostDetail(@PathVariable("id") Integer id) {
        return adminService.getPostDetail(id);
    }
}
