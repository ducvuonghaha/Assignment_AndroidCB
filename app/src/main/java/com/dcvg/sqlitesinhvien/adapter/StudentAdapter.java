package com.dcvg.sqlitesinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.sqlitesinhvien.R;
import com.dcvg.sqlitesinhvien.model.Student;

import java.util.List;

import static com.dcvg.sqlitesinhvien.activity.StudentActivity.edtBirthStudent;
import static com.dcvg.sqlitesinhvien.activity.StudentActivity.edtNameStudent;
import static com.dcvg.sqlitesinhvien.activity.StudentActivity.idStudent;
import static com.dcvg.sqlitesinhvien.activity.StudentActivity.spnClass;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.student = studentList.get(position);
        holder.tvIdStudent.setText(String.valueOf(holder.student.getId()));
        holder.tvNameStudent.setText(holder.student.getName());
        holder.tvMajorStudent.setText(holder.student.getMajor());
        holder.tvClassOfStudent.setText(holder.student.getClassStudent());
        holder.tvBirthStudent.setText(holder.student.getBirth());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNameStudent.setText(holder.student.getName());
                edtBirthStudent.setText(holder.student.getBirth());

                for(int i= 0; i < spnClass.getAdapter().getCount(); i++)
                {
                    if(spnClass.getAdapter().getItem(i).toString().contains(holder.student.getClassStudent()))
                    {
                        spnClass.setSelection(i);
                    }
                }

                idStudent = holder.student.getId();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvIdStudent;
        private TextView tvNameStudent;
        private TextView tvClassOfStudent;
        private TextView tvMajorStudent;
        private TextView tvBirthStudent;
        private Student student;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvIdStudent = (TextView) itemView.findViewById(R.id.tvIdStudent);
            tvNameStudent = (TextView) itemView.findViewById(R.id.tvNameStudent);
            tvClassOfStudent = (TextView) itemView.findViewById(R.id.tvClassOfStudent);
            tvMajorStudent = (TextView) itemView.findViewById(R.id.tvMajorStudent);
            tvBirthStudent = (TextView) itemView.findViewById(R.id.tvBirthStudent);
        }
    }
}
