package ir.shalior.stroiesforkids.activities.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.OptionListsActivity_;
import ir.shalior.stroiesforkids.model.Story;

import static ir.shalior.stroiesforkids.model.StoryBoxer.STORY_IS_LOCKED;

class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {
    private Context mContex;
    private List<Story> mDataset;
    // Provide a suitable constructor (depends on the kind of dataset)
    public StoriesAdapter(List<Story> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StoriesAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        mContex = parent.getContext();
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story_ma, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Story currentStory = mDataset.get(position);
        holder.textView.setText(mDataset.get(position).getName());

        if (currentStory.getIsLocked() == STORY_IS_LOCKED) {
            holder.view.setAlpha(0.95f);
            holder.lochIcon.setVisibility(View.VISIBLE);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //silence
                }
            });
        } else {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //anOtherWAt:
                    OptionListsActivity_.intent(mContex).extra("storyId", mDataset.get(position).id).start();
                }
            });
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        // each data item is just a string in this case
        TextView textView;
        ImageView storyImage;
        ImageView lochIcon;

        MyViewHolder(View v) {
            super(v);
            this.view = v;
            this.textView = v.findViewById(R.id.storyName);
            this.storyImage = v.findViewById(R.id.storyImage);
            this.lochIcon = v.findViewById(R.id.storyLockIcon);
        }
    }
}
