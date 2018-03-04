package cit.mini.tp.techpedia;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.widget.Filter;


public class BookListItemActivity extends BaseAdapter implements Filterable{


    //the hero list that will be displayed
    private BookListActivity activity;
    private ArrayList<SellBook> booklist;
    private ArrayList<SellBook> filteredList;
    private LayoutInflater mInflater;
    private ItemFilter itemFilter;
    private Typeface typeface;



    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
//        public BookListItemActivity(List<SellBook> booklist, Context mCtx) {
//            super(mCtx, R.layout.activity_list_item, booklist);
//            this.booklist = booklist;
//            this.mCtx = mCtx;
//            searchlist=booklist;
//            getFilter();
//
//        }

    public BookListItemActivity(BookListActivity activity, ArrayList<SellBook> booklist) {
        this.activity = activity;
        this.booklist = booklist;
        this.filteredList = booklist;
        // typeface = Typeface.createFromAsset(activity.getAssets(), "fonts/vegur_2.otf");

        getFilter();
    }
    /**
     * Get size of user list
     * @return userList size
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }

    /**
     * Get specific item from user list
     * @param i item index
     * @return list item
     */
    @Override
    public SellBook getItem(int i) {
        return filteredList.get(i);
    }

    public List<SellBook> getFilteredItem(){
        return filteredList;
    }
    /**
     * Get user list item id
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }


    //this method will return the list item
    @Override
    public View getView(int position, View view, ViewGroup parent) {
//            //getting the layoutinflater
//            LayoutInflater inflater = LayoutInflater.from(mCtx);
//
//            //creating a view with our xml layout
//            View listViewItem = inflater.inflate(R.layout.activity_list_item, null, true);
//
//            //getting text views
//            TextView BName = listViewItem.findViewById(R.id.BookName);
//           /* TextView BAuthor = listViewItem.findViewById(R.id.BookAuthor);
//            TextView BOwner=listViewItem.findViewById(R.id.BookOwner);
//*/
//            //Getting the hero for the specified position
//            SellBook books = booklist.get(position);
//
//            //setting hero values to textviews
//            BName.setText(books.getName());
//          /*  BOwner.setText(books.getOwner());
//            BAuthor.setText(books.getAuthor());*/
//
//            //returning the listitem
//            return listViewItem;
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        final ViewHolder holder;
        final SellBook user = (SellBook) getItem(position);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_list_item, parent, false);
            holder = new ViewHolder();
            holder.iconText = (TextView) view.findViewById(R.id.BookName);
            holder.name = (TextView) view.findViewById(R.id.BookName);
            //holder.iconText.setTypeface(typeface, Typeface.BOLD);
            //holder.iconText.setTextColor(activity.getResources().getColor(R.color.white));
            // holder.name.setTypeface(typeface, Typeface.NORMAL);

            view.setTag(holder);
        } else {
            // get view holder back
            holder = (ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use
        holder.iconText.setText("#");
        holder.name.setText(user.getName());
        view.setBackgroundResource(R.drawable.ic_search_white);

        return view;
    }

    /**
     * Get custom filter
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (itemFilter == null) {
            itemFilter = new ItemFilter();
        }

        return itemFilter;
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        TextView iconText;
        TextView name;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<SellBook> tempList = new ArrayList<SellBook>();

                // search content in friend list
                for (SellBook user : booklist) {
                    if (user.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(user);
                    }
                }
               // booklist.addAll(tempList);

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = booklist.size();
                filterResults.values = booklist;
            }
           // booklist.clear();


            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<SellBook>) results.values;

            notifyDataSetChanged();
        }
    }

}