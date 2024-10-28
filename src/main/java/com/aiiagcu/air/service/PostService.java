package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.PostInput;
import com.aiiagcu.air.dto.PostList;
import com.aiiagcu.air.dto.PostListItem;
import com.aiiagcu.air.dto.PostOutput;
import com.aiiagcu.air.dto.token.IssueTokenRequest;
import com.aiiagcu.air.dto.token.Issuer;
import com.aiiagcu.air.dto.token.Permission;
import com.aiiagcu.air.entity.Post;
import com.aiiagcu.air.entity.PostBlock;
import com.aiiagcu.air.entity.PostBlockType;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.repository.PostRepository;
import com.aiiagcu.air.util.Base62;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostInput save(PostInput postInput) {
        // TODO: 관리자만 작성 가능
        Post postEntity = Post.toSaveEntity(postInput);
        postRepository.save(postEntity);

        return PostInput.toSaveDTO(postEntity);
    }

    @Transactional
    public PostInput update(PostInput newPostInput, String id, User user) {
        // ID 존재 확인
        Optional<Post> targetEntity = postRepository.findById(Base62.fromBase62(id));
        if (targetEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Not found");

        // 작성자 일치 여부 확인
        final Post originalPost = targetEntity.get();
        if (!originalPost.getAuthor().equals(user))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can edit your post only");

        originalPost.setTitle(newPostInput.getTitle());
        originalPost.setBlocks(newPostInput.getBlocks());

        return PostInput.toSaveDTO(originalPost);
    }

    public PostList getPostList(int pageNumber) {
        PostList postList = new PostList();

        final Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.DESC, "createdTime");
        final Page<Post> post = postRepository.findAll(pageable);
        final Long count = pageNumber == 0 ? postRepository.count() : null;

        List<PostListItem> dtoList = new ArrayList<>();
        for (Post e : post) {
            dtoList.add(PostListItem.toSaveDTO(e));
        }

        postList.setCount(count);
        postList.setPosts(dtoList);

        return postList;
    }

    public PostOutput getPost(String id) {
        Optional<Post> post = postRepository.findById(Base62.fromBase62(id));
        if (post.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Not found");

        Post postEntity = post.get();

        for (PostBlock block : postEntity.getBlocks()) {
            if (block.getType() == PostBlockType.Image) {
                try {
                    if (block.getContent() != null) {
                        block.setContent(Base64.getEncoder().encodeToString(new FileUrlResource(block.getContent()).getContentAsByteArray()));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return PostOutput.toSaveDTO(postEntity);
    }

    public static URI appendQueryParam(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());
    }
}
