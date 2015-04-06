package ru.avsidorov.github.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.avsidorov.github.MODELS.GHCommit;
import ru.avsidorov.github.R;

/**
 * Created by Сидоров on 05.04.2015.
 */
public class CommitsAdapter extends ArrayAdapter<GHCommit> {
    public CommitsAdapter(Context context, int resource, ArrayList<GHCommit> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        View rowView = convertView;
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.row_commit, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) rowView.findViewById(R.id.comit_descriptionTextView);
            holder.commitsDescription = (TextView) rowView.findViewById(R.id.authorTextView);
            holder.sha = (TextView) rowView.findViewById(R.id.shaTextView);
            holder.date = (TextView) rowView.findViewById(R.id.dateTextView);
            holder.authorAvatar = (ImageView) rowView.findViewById(R.id.avatarAuthorImageView);

            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        final GHCommit commits = (GHCommit) getItem(position);

        holder.date.setText(commits.getCommit().getAuthor().getDate());//TODO Сделать преобразование строки в удобоваримый вид
        holder.name.setText(commits.getCommit().getAuthor().getName());
        holder.commitsDescription.setText(commits.getCommit().getMessage());
        holder.sha.setText(commits.getSha().substring(0, 5));

        Picasso.with(getContext())
                .load(commits.getAuthor().getAvatarUrl())
                .placeholder(R.drawable.github_48)
                .fit()
                .centerInside()
                .into(holder.authorAvatar);


        return rowView;
    }

    static class ViewHolder {
        TextView name;
        TextView commitsDescription;
        TextView sha;
        TextView date;
        ImageView authorAvatar;


    }
}
