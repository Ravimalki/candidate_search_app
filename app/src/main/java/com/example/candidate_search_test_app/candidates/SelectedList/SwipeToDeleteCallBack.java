package com.example.candidate_search_test_app.candidates.SelectedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.candidate_search_test_app.R;

/**
 * Created by Koshini Bulathsinhala
 */

abstract public class SwipeToDeleteCallBack extends ItemTouchHelper.Callback {
    private Context context;
    private Paint paint;
    private ColorDrawable colorDrawable;
    private int backgroundColor;
    private Drawable drawable;
    private int intrinsicWidth, intrinsicHeight;

    public SwipeToDeleteCallBack(Context context) {
        this.context = context;
        colorDrawable = new ColorDrawable();
        backgroundColor = Color.parseColor("#f01111");
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_24);
        assert drawable != null;
        intrinsicWidth = drawable.getIntrinsicWidth();
        intrinsicHeight = drawable.getIntrinsicHeight();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View view = viewHolder.itemView;
        int itemHeight = view.getHeight();

        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        if (isCancelled) {
            clearCanvas(c, view.getRight() + dX, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        colorDrawable.setColor(backgroundColor);
        colorDrawable.setBounds(view.getRight() + (int) dX, view.getTop(), view.getRight(), view.getBottom());
        colorDrawable.draw(c);

        int deleteIconTop = view.getTop() + (itemHeight - intrinsicHeight) / 2;
        int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
        int deleteIconLeft = view.getRight() - deleteIconMargin - intrinsicWidth;
        int deleteIconRight = view.getRight() - deleteIconMargin;
        int deleteIconBottom = deleteIconTop + intrinsicHeight;

        drawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        drawable.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void clearCanvas(Canvas canvas, Float left, Float top, Float right, Float bottom) {
        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.6f;
    }
}
