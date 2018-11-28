package com.cc.dapp.manager.api.controller;

import com.cc.dapp.manager.api.pojo.dto.UserDTO;
import com.cc.dapp.manager.api.pojo.vo.UserVO;
import com.cc.dapp.manager.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("取得用户列表")
    @GetMapping
    public ResponseEntity<List<UserVO>> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation("修改用户")
    @PutMapping("/{userId}")
    public ResponseEntity<UserVO> upateUser(@PathParam ("userId") String userId,
                                            @RequestBody @Valid UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }
}

/*
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public PageInfo<User> getUserList(@RequestParam(name="pageNum", defaultValue="1") Integer pageNum,
                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                      @RequestParam(name="queryUser") User queryUser) {
        return userService.pageList(queryUser, pageNum, pageSize);
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateDbAndCache(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
    }

}
*/