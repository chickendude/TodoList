package ch.ralena.todolist.ui.fragments;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.ralena.todolist.R;
import ch.ralena.todolist.adapters.MainAdapter;
import ch.ralena.todolist.data.local.sql.SqlManager;
import ch.ralena.todolist.data.models.Todo;
import ch.ralena.todolist.data.models.TodoList;
import ch.ralena.todolist.ui.activities.MainActivity3;
import ch.ralena.todolist.ui.itemhelper.ItemDragListener;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class MainFragment extends Fragment implements MainAdapter.OnDataChangedListener, MainAdapter.OnItemClickedListener, ItemDragListener {
	// constants
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String TAG_TODO_LISTS = "todo_lists";
	public static final String TAG_TRANSITION_NAME = "transition_name";
	public static final String TAG_TRANSITION_RELATIVELAYOUT = "transition_relative_layout";
	private static final String TAG_TODO_LIST_FRAGMENT = "todo_list_fragment";

	// ItemTouch stuff
	ItemTouchHelper mItemTouchHelper;

	// member fields
	private ArrayList<TodoList> mTodoLists;
	MainAdapter mAdapter;
	SqlManager mSqlManager;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mSqlManager = new SqlManager(getActivity());
		if (savedInstanceState == null) {
			mTodoLists = mSqlManager.getTodoLists();
		} else {
			mTodoLists = savedInstanceState.getParcelableArrayList(TAG_TODO_LISTS);
		}

		View view = inflater.inflate(R.layout.fragment_main, container, false);

		// show FAB
		((MainActivity3) getActivity()).showFab();

		// set up RecyclerView and adapter
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		mAdapter = new MainAdapter(mTodoLists, this);
		recyclerView.setAdapter(mAdapter);
		// prepare LayoutManager
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		// set up itemtouchhelper callback
		ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
			@Override
			public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
				int mFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
				int sFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
				return makeMovementFlags(mFlags, sFlags);
			}

			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
				mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
				return true;
			}

			@Override
			public boolean isLongPressDragEnabled() {
				return false;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

			}
		};
		mItemTouchHelper = new ItemTouchHelper(callback);
		mItemTouchHelper.attachToRecyclerView(recyclerView);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList(TAG_TODO_LISTS, mTodoLists);
		super.onSaveInstanceState(outState);
	}

	public void addTodoList(TodoList todoList) {
		todoList.setPosition(mTodoLists.size());
		long id = mSqlManager.createTodoList(todoList);
		todoList.setId(id);
		mTodoLists.add(todoList);
		mAdapter.updateTodoList(mTodoLists);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDeleteClicked(TodoList todoList) {
		mSqlManager.deleteTodoList(todoList);
		mTodoLists.remove(todoList);
		mAdapter.updateTodoList(mTodoLists);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onTitleEdited(TodoList todoList) {
		mSqlManager.updateTodoListTitle(todoList);
	}

	@Override
	public void onCompletionStatusChanged(final TodoList todoList) {
		// make sure todoitems match todolist
		for (Todo todo : todoList.getTodoItems()) {
			todo.setCompleted(todoList.isCompleted());
		}
		// save changes in database
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (Todo todo : todoList.getTodoItems()) {
					todo.setCompleted(todoList.isCompleted());
					mSqlManager.updateTodoItemCompleted(todo);
				}
				mSqlManager.updateTodoListCompleted(todoList);
			}
		});
		mSqlManager.updateTodoListCompleted(todoList);
	}

	@Override
	public void onPositionChanged(TodoList todoList) {
		mSqlManager.updateTodoListPosition(todoList);
	}

	@Override
	public void onOpenTodoList(TodoList todoList, View view) {
		// hide FAB
//		((MainActivity) getActivity()).hideFab();

		// get parent relative layout
		RelativeLayout relativeLayout = (RelativeLayout) view.getParent();

		TodoListFragment todoListFragment = new TodoListFragment();

		// create transition set for shared elements
		TransitionSet transitionSet = new TransitionSet();
		transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
		transitionSet.addTransition(new ChangeBounds());
		transitionSet.addTransition(new ChangeTransform());
		transitionSet.addTransition(new ChangeClipBounds());

		// add transition set to new fragment and current fragment
		todoListFragment.setSharedElementEnterTransition(transitionSet);
		setSharedElementReturnTransition(transitionSet);

		// create other transitions
		todoListFragment.setEnterTransition(new Fade());
		todoListFragment.setReturnTransition(new Fade());
		todoListFragment.setExitTransition(new Fade());

		setExitTransition(new Fade());
		setReenterTransition(null);

		String titleViewTransitionName = view.getTransitionName();
		String relativeLayoutTransitionName = ((RelativeLayout)view.getParent()).getTransitionName();
		Log.d(TAG, relativeLayoutTransitionName);

		Bundle bundle = new Bundle();
		bundle.putParcelable(TAG_TODO_LISTS, todoList);
		bundle.putString(TAG_TRANSITION_NAME, titleViewTransitionName);
		bundle.putString(TAG_TRANSITION_RELATIVELAYOUT, relativeLayoutTransitionName);
		todoListFragment.setArguments(bundle);

		getFragmentManager().beginTransaction()
				.replace(R.id.placeHolder, todoListFragment)
				.addSharedElement(view, titleViewTransitionName)
				.addSharedElement(relativeLayout, relativeLayoutTransitionName)
				.addToBackStack(TAG_TODO_LIST_FRAGMENT)
				.commit();
	}

	@Override
	public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
		mItemTouchHelper.startDrag(viewHolder);
	}
}
