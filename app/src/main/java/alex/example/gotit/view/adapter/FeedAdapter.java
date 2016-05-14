package alex.example.gotit.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import alex.example.gotit.R;
import alex.example.gotit.controls.VolleyInstance;
import alex.example.gotit.model.Feed;
import alex.example.gotit.model.Status;

/**
 * Created by Alex on 5/12/2016.
 */
public class FeedAdapter extends ListItemAdapter<Feed> {

    private final VolleyInstance instance;
    private final ImageLoader imageLoader;

    public FeedAdapter(Context context) {
        super(context);

        instance = VolleyInstance.getInstance(context);
        imageLoader = instance.getImageLoader();
    }

    @Override
    protected View getViewAt(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.feed_item, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        // populateView
        Feed item = getItem(position);

        // load attachment
        if (holder.imgAttachment instanceof NetworkImageView) {
            ((NetworkImageView) holder.imgAttachment).setImageUrl(item.getAttachment(), imageLoader);
        }

        // load author avatar
        if (holder.imgAuthorAvatar instanceof NetworkImageView) {
            ((NetworkImageView) holder.imgAuthorAvatar).setImageUrl(item.getAuthorAvatar(), imageLoader);
        }

        Status status = item.getStatus();
        switch (status) {
            case EXPLAINED:
                holder.tvStatus.setText("Explained");
                holder.tvAuthor.setText("Explained by " + item.getAuthor());
                break;
            case IN_PROGRESS:
                holder.tvStatus.setText("In Progress");
                break;
        }

        Date date = new Date(item.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.tvTime.setText(simpleDateFormat.format(date));

        return convertView;
    }

    static class ViewHolder {
        TextView tvStatus;
        ImageView imgAttachment;
        TextView tvAuthor;
        TextView tvTime;
        ImageView imgAuthorAvatar;

        public ViewHolder(View convertView) {
            tvStatus = (TextView) convertView.findViewById(R.id.feed_status);
            imgAttachment = (ImageView) convertView.findViewById(R.id.feed_attachment);
            tvAuthor = (TextView) convertView.findViewById(R.id.feed_author);
            imgAuthorAvatar = (ImageView) convertView.findViewById(R.id.feed_authorPhoto);
            tvTime = (TextView) convertView.findViewById(R.id.feed_time);
        }
    }
}
