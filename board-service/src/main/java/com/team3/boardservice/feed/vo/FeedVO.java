package com.team3.boardservice.feed.vo;

import com.team3.boardservice.feed.dto.ImgDTO;
import com.team3.boardservice.reple.aggregate.CommentEntity;
import com.team3.boardservice.reple.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FeedVO {
        private int boardId;
        private String boardTitile;
        private String boardContent;
        private String writeDate;
        private int hits;
        private int reportCount;
        private int isActive;
        private long memberId;
        private ImgDTO imgId;
        private List<CommentDTO> comments;
}
