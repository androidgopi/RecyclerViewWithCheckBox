package org.pratap.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.pratap.MainActivity;
import org.pratap.R;
import org.pratap.models.Student;

import java.util.List;

public class CardViewDataAdapter extends
		RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

	private List<Student> stList;
	MainActivity callBack;

	public CardViewDataAdapter(List<Student> students) {
		this.stList = students;

	}

	// Create new views
	@Override
	public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.cardview_row, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, int position) {

		final int pos = position;
		Student st=stList.get(position);

		viewHolder.tvName.setText(st.getName());

		viewHolder.tvEmailId.setText(st.getEmailId());

		viewHolder.chkSelected.setChecked(st.isSelected());

		viewHolder.chkSelected.setTag(st);
		viewHolder.chkSelected.setContentDescription(String.valueOf(st));


// if click selectAll Button that time it's used
		if(viewHolder.chkSelected.isChecked()){
			viewHolder.tvEmailId.setVisibility(View.VISIBLE);
		}else {
			viewHolder.tvEmailId.setVisibility(View.GONE);
		}

		viewHolder.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(viewHolder.chkSelected.isChecked()){
					viewHolder.tvEmailId.setVisibility(View.VISIBLE);
				}else {
					viewHolder.tvEmailId.setVisibility(View.GONE);
				}
			}
		});
		
		viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				Student contact = (Student) cb.getTag();

				contact.setSelected(cb.isChecked());
				stList.get(pos).setSelected(cb.isChecked());
				callBack.selcetCheckBox( (Student) v.getTag());

			}
		});

	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return stList.size();
	}

	public void setCallBack(MainActivity callback) {
		this.callBack = callback;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvName;
		public TextView tvEmailId;

		public CheckBox chkSelected;

		public Student singlestudent;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);

			tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);

			tvEmailId = (TextView) itemLayoutView.findViewById(R.id.tvEmailId);
			chkSelected = (CheckBox) itemLayoutView.findViewById(R.id.chkSelected);

		}

	}



	// method to access in activity after updating selection
	public List<Student> getStudentist() {
		return stList;
	}

}
