package DSite.domain.Board.service;

import DSite.domain.Board.dto.BoardDto;
import DSite.domain.Board.domain.BoardEntity;
import DSite.domain.Board.domain.repository.BoardRepository;
import DSite.domain.Board.exception.BoardUrlStrangeException;
import DSite.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<BaseResponse> create(BoardDto boardDto) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        Document document = Jsoup.connect(boardDto.getUrl()).get();
        String ogTitle = document.select("meta[property=og:title]").attr("content"); //제목
        String ogImage = document.select("meta[property=og:image]").attr("content"); //이미지
        String ogDescription = document.select("meta[property=og:description]").attr("content"); //설명
        if (ogTitle.isEmpty() && ogDescription.isEmpty()) throw BoardUrlStrangeException.EXCEPTION;
        else {
            if (ogImage.isEmpty()) {
                boardDto.setImage("https://hook-s3-innosync.s3.ap-northeast-2.amazonaws.com/images/Group+50.png");
            } else {
                boardDto.setImage(ogImage);
            }
            boardDto.setTitle(ogTitle);
            boardDto.setDescription(ogDescription);
            BoardEntity boardEntity = dtoToEntity(boardDto);


            String url = boardDto.getUrl();

            // 중복 URL 체크
            Optional<BoardEntity> existingEntity = boardRepository.findByUrl(url);

            if (existingEntity.isPresent()) {
                baseResponse.setStatus(HttpStatus.BAD_REQUEST);
                baseResponse.setMessage("이미 동일한 URL을 가진 게시글이 존재합니다.");
                return ResponseEntity.badRequest().body(baseResponse);
            } else {

                try {
                    boardRepository.save(boardEntity);
                    baseResponse.setStatus(HttpStatus.CREATED);
                    baseResponse.setMessage("게시글 작성 성공");
                    return ResponseEntity.ok().body(baseResponse);
                } catch (DataAccessException e) {
                    baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    baseResponse.setMessage(e.getMessage());
                    return ResponseEntity.badRequest().body(baseResponse);
                }
            }
        }


    }

    @Override
    public ResponseEntity<BaseResponse> getContents(Long id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Optional<BoardEntity> boardEntity= boardRepository.findById(id);
            if (boardEntity.isPresent()) {
                baseResponse.of(HttpStatus.OK, "Find", boardEntity.get());
                return ResponseEntity.ok(baseResponse);
            } else {
                baseResponse.setStatus(HttpStatus.NOT_FOUND);
                baseResponse.setMessage("Board id not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
            }
        } catch (Exception e) {
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            baseResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }

    }

    @Override
    public ResponseEntity<BaseResponse> getAllContents() {
        BaseResponse baseResponse = new BaseResponse();
        try{
            List<BoardEntity> boardEntities = boardRepository.findAll();
            if (!boardEntities.isEmpty()){
                List<BoardDto> boardDtos = boardEntities.stream()  // 코드 중복성 이거 나중에 함수로 빼서 리펙토링
                        .map(this::entityToDto)
                        .collect(Collectors.toList());

                //역순으로 데이터 반환
                Collections.reverse(boardDtos);
                List<BoardDto> list = boardDtos.stream().limit(20).collect(Collectors.toList());

                baseResponse.setStatus(HttpStatus.OK);
                baseResponse.setMessage("성공");
                baseResponse.setData(list);
                return ResponseEntity.ok(baseResponse);
            }
            else {
                baseResponse.setStatus(HttpStatus.NOT_FOUND);
                baseResponse.setMessage("NOT_FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
            }

        } catch (Exception e){
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            baseResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getCategoryContents(String category) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<BoardEntity> boardEntity = boardRepository.findByCategoryContaining(category);
            if (!boardEntity.isEmpty()){
                List<BoardDto> boardDtos = boardEntity.stream()
                        .map(this::entityToDto)
                        .collect(Collectors.toList());
                Collections.reverse(boardDtos);

                baseResponse.setStatus(HttpStatus.OK);
                baseResponse.setMessage("성공");
                baseResponse.setData(boardDtos);
                return ResponseEntity.ok(baseResponse);
            }
            else {
                baseResponse.setMessage(HttpStatus.NOT_FOUND);
                baseResponse.setMessage("NOT_FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
            }
        } catch (Exception e){
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }


}
