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

import ru.avsidorov.github.MODELS.GHRepositoryFull;
import ru.avsidorov.github.R;

/**
 * Created by Сидоров on 05.04.2015.
 */
public class RepositoryAdapter extends ArrayAdapter<GHRepositoryFull> {
    Context context;

    public RepositoryAdapter(Context cont, int resource, ArrayList<GHRepositoryFull> objects) {
        super(cont, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        View rowView = convertView;
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.row_repos, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) rowView.findViewById(R.id.comit_descriptionTextView);
            holder.description = (TextView) rowView.findViewById(R.id.authorTextView);
            holder.authorAvatar = (ImageView) rowView.findViewById(R.id.repoLogoImageView);
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        final GHRepositoryFull repository = (GHRepositoryFull) getItem(position);


        holder.name.setText(repository.getFullName());
        holder.description.setText(repository.getDescription());
        Picasso.with(getContext())
                .load(repository.getOwner().getAvatarUrl())
                .fit()
                .centerInside()
                .into(holder.authorAvatar);
        return rowView;
    }

    static class ViewHolder {
        TextView name;
        TextView description;
        ImageView authorAvatar;

    }
}
