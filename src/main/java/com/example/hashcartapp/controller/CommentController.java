package com.example.hashcartapp.controller;

import com.example.hashcartapp.dto.CommentDTO;
import com.example.hashcartapp.response.ApiResponse;
import com.example.hashcartapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * It shows all the comments
     * @return It returns details of all the comments
     */

    @GetMapping("/comment")
    public List<CommentDTO> getComments() {
        return commentService.getAllComments();
    }

    /**
     * It shows the detail of comment on any particular id
     * @param commentId
     * @return It returns the detail of any comment on particular comment id
     */
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId){

        CommentDTO commentDTO = commentService.getCommentById(commentId);
        if (commentDTO != null) {
            return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
    }

    /**
     * User can do comment on any advertisement
     * @param commentDTO
     * @param advertisementId
     * @return User post comment on the particular advertisement
     */

     @PostMapping("/advertisement/{advertisementId}/comment")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long advertisementId){

         CommentDTO comment = this.commentService.createComment(commentDTO,advertisementId);

         return new ResponseEntity<CommentDTO>(comment, HttpStatus.CREATED);
     }

    /**
     * User can delete a comment
     * @param commentId
     * @return User can delete comment on the particular advertisement
     */

     @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@Valid @PathVariable Long commentId){

         this.commentService.deleteComment(commentId);

         return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
     }

}
