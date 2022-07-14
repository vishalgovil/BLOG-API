package blogappapis.payloads;



import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CommentDto {

	private Integer id;
	
	private String content;
	}
