package fr.eni.abono.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.models.Subscription;

public class SubscriptionAdapter extends ArrayAdapter<Subscription> {

    private int resId;

    public SubscriptionAdapter(@NonNull Context context, int resource, @NonNull List<Subscription> objects) {
        super(context, resource, objects);

        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder myViewHolder;

        if(convertView == null) {
            // chargement du layout R.layout.item_restaurant
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);

            myViewHolder = new ViewHolder();

            myViewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            myViewHolder.textViewPrice = convertView.findViewById(R.id.textViewPrice);
            myViewHolder.textViewPriority = convertView.findViewById(R.id.textViewPriority);

            convertView.setTag(myViewHolder); // enregistrement du ViewHolder
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        Subscription item = getItem(position);

        myViewHolder.textViewName.setText(item.getName());
        myViewHolder.textViewPrice.setText(String.valueOf(item.getPrice()));
        myViewHolder.textViewPriority.setText(String.valueOf(item.getPriority()));

        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewPrice, textViewPriority;
    }
}
