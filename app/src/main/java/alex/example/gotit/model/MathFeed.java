package alex.example.gotit.model;

/**
 * Created by Alex on 5/12/2016.
 */
public class MathFeed implements Feed {
    Status status;
    String attachmentUrl;
    String author;
    long time;
    String authorAvatarUrl;

    public MathFeed(Status status, String attachmentUrl, String author, long time, String authorAvatarUrl) {
        this.status = status;
        this.attachmentUrl = attachmentUrl;
        this.author = author;
        this.time = time;
        this.authorAvatarUrl = authorAvatarUrl;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getAttachment() {
        return attachmentUrl;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public String getAuthorAvatar() {
        return authorAvatarUrl;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ProcessState ");
        builder.append(getStatus());

        builder.append("Expert Name ");
        builder.append(getAuthor());

        builder.append("Created time ");
        builder.append(getTime());

        builder.append("Student Avatar ");
        builder.append(getAuthorAvatar());

        builder.append("Attachment ");
        builder.append(getAttachment());

        return builder.toString();
    }
}
