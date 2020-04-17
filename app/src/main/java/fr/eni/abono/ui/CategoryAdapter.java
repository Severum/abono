package fr.eni.abono.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.List;

import fr.eni.abono.R;
import fr.eni.abono.bo.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private int resId;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder myViewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);
            myViewHolder = new ViewHolder();
            myViewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            myViewHolder.textViewTotal = convertView.findViewById(R.id.textViewTotal);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (CategoryAdapter.ViewHolder) convertView.getTag();
        }
        Category item = getItem(position);
        myViewHolder.textViewName.setText(item.getName());
        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewTotal;
    }
}
