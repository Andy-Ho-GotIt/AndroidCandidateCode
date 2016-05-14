package alex.example.gotit.model;

/**
 * Created by Alex on 5/12/2016.
 */
public interface Feed {
    Status getStatus();
    String getAttachment();
    String getAuthor();
    long getTime();
    String getAuthorAvatar();
}
