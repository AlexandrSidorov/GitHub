package ru.avsidorov.github.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.avsidorov.github.MODELS.GHCommit;
import ru.avsidorov.github.R;

/**
 * Created by Сидоров on 05.04.2015.
 */
public class CommitsAdapter extends ArrayAdapter<GHCommit> {
    Locale local = new Locale("ru", "RU");
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
        try {
            //GregorianCalendar mydate = new GregorianCalendar(local);

            Date date = StringToDate(commits.getCommit().getAuthor().getDate());
            date.toLocaleString();

            // mydate.setTime(date);


            holder.date.setText(date.toLocaleString()); //Calendar отбрасывал 0 в  минутах и часах, поэтому пришлось использовать deprecated метод(
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // holder.date.setText(commits.getCommit().getAuthor().getDate());//TODO Сделать преобразование строки в удобоваримый вид
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

    private Date StringToDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss", Locale.ENGLISH);
        return format.parse(dateString);
    }/*
    private String getTime(Calendar calendar){
      //  String result = calendar.getDisplayName(Calendar.HOUR,Calendar.SHORT,local)+":"+calendar.getDisplayName(Calendar.MINUTE,Calendar.SHORT,local)+" "
        //        +calendar.getDisplayName(Calendar.DAY_OF_MONTH,Calendar.SHORT,local)+"-"+(calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,local))+"-"+calendar.getDisplayName(Calendar.YEAR,Calendar.SHORT,local);
String result = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" "
                +calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);

        return result;
    }*/

    static class ViewHolder {
        TextView name;
        TextView commitsDescription;
        TextView sha;
        TextView date;
        ImageView authorAvatar;


    }
}
