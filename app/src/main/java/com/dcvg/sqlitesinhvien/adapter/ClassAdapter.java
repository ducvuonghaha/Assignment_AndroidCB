package com.dcvg.sqlitesinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.model.Class;

import java.util.List;

import static com.dcvg.sqlitesinhvien.activity.ClassActivity.edtMajorClass;
import static com.dcvg.sqlitesinhvien.activity.ClassActivity.edtNameClass;
import static com.dcvg.sqlitesinhvien.activity.ClassActivity.id;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.Holder> {

    private Context context;
    private List<Class> classList;


    public ClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.classModel = classList.get(position);
        holder.tvIdClass.setText(String.valueOf(holder.classModel.getId()));
        holder.tvNameClass.setText(holder.classModel.getName());
        holder.tvMajorClass.setText(holder.classModel.getMajor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.classModel.getId();
                edtNameClass.setText(holder.classModel.getName());
                edtMajorClass.setText(holder.classModel.getMajor());
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private Class classModel;
        private TextView tvIdClass;
        private TextView tvNameClass;
        private TextView tvMajorClass;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvIdClass = (TextView) itemView.findViewById(R.id.tvIdClass);
            tvNameClass = (TextView) itemView.findViewById(R.id.tvNameClass);
            tvMajorClass = (TextView) itemView.findViewById(R.id.tvMajorClass);
        }
    }
}
