package com.example.simplenote;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note , NoteAdapter.NoteViewHolder> {

   // private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    public NoteAdapter() {
        super( DIFF_CALLBACK );
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK  = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }


        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals( newItem.getTitle()) &&
                    oldItem.getDescription().equals( newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };



    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.note_row, parent, false );
        return new NoteViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = getItem( position );
        holder.titleTextView.setText( currentNote.getTitle() );
        holder.descriptionTextView.setText( currentNote.getDescription() );
        holder.priorityTextView.setText( String.valueOf( currentNote.getPriority() ) );


    }

    /*
    @Override
    public int getItemCount() {
        return notes.size();
    }

     */


    /*
    public void setNotes(List<Note> notes)    //------------------------------------------------
    {
        this.notes = notes;
        notifyDataSetChanged();

    }

     */

    public Note getNoteAt(int position) {
        return getItem( position );

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView priorityTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super( itemView );
            titleTextView = itemView.findViewById( R.id.title_text_view );
            descriptionTextView = itemView.findViewById( R.id.description_text_view );
            priorityTextView = itemView.findViewById( R.id.priority_text_view );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick( getItem( position ) );
                    }

                }
            } );

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;


    }


}
