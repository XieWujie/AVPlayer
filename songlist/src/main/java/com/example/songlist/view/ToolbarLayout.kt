package com.example.songlist.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.songlist.R
import com.example.songlist.extentions.visibility
import kotlinx.android.synthetic.main.toolbar.view.*

class ToolbarLayout : FrameLayout {
    /**
     * ToolBar背景颜色
     */
    var toolbarBackgroundColor = Color.parseColor("#00000000")
        set(value) {
            field = value
            toolbarBase?.setBackgroundColor(Color.parseColor(value.toString()))
        }

    /**
     * Back 按钮
     */
    var showBack: Boolean = true
        set(value) {
            field = value
            toolbarBack?.visibility(value)
        }

    /**
     * 左边TextView
     */
    var showLeftText: Boolean = false
        set(value) {
            field = value
            toolbarLeftText?.visibility(value)
        }
    var leftText: CharSequence? = ""
        set(value) {
            field = value
            toolbarLeftText?.text = value
        }
    var leftTextColor = resources.getColor(R.color.textColor)
        set(value) {
            field = value
            toolbarLeftText?.setTextColor(Color.parseColor(value.toString()))
        }

    /**
     * 左边title and tips Text
     */
    var showLeftFrameLayout: Boolean = false
        set(value) {
            field = value
            toolBarLeftFrameLayout?.visibility(value)
        }
    var leftTitleText: CharSequence? = ""
        set(value) {
            field = value
            toolbarLeftTitleText?.text = value
        }
    var leftTitleTextColor = resources.getColor(R.color.textColor)
        set(value) {
            field = value
            toolbarLeftTitleText?.setTextColor(Color.parseColor(value.toString()))
        }
    var leftTipsText: CharSequence? = ""
        set(value) {
            field = value
            toolbarLeftTipsText?.text = value
        }
    var leftTipsTextColor = resources.getColor(R.color.textColor)
        set(value) {
            field = value
            toolbarLeftTipsText?.setTextColor(Color.parseColor(value.toString()))
        }

    var showEditText: Boolean = false
        set(value) {
            field = value
            toolbarEditText?.visibility(value)
        }

    /**
     * 右边 text
     */
    var showRightText: Boolean = false
        set(value) {
            field = value
            toolbarRightText?.visibility(value)
        }
    var rightText: CharSequence? = ""
        set(value) {
            field = value
            toolbarRightText?.text = value
        }
    var rightTextColor = resources.getColor(R.color.textColor)
        set(value) {
            field = value
            toolbarRightText?.setTextColor(Color.parseColor(value.toString()))
        }

    var showLine: Boolean = false
        set(value) {
            field = value
            toolbarLine?.visibility(value)
        }

    /**
     * 下方未实现完全
     */

    var showRightFirstIcon: Boolean = false
        set(value) {
            field = value
            toolBarRightFirstIcon?.visibility(value)
        }

    var showRightSecondIcon: Boolean = false
        set(value) {
            field = value
            toolBarRightSecondIcon?.visibility(value)
        }


    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.toolbar, this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ToolbarLayout)
            typedArray.apply {
                showBack = getBoolean(R.styleable.ToolbarLayout_showBack, true)
                showLeftText = getBoolean(R.styleable.ToolbarLayout_showLeftText, false)
                showLeftFrameLayout =
                    getBoolean(R.styleable.ToolbarLayout_showLeftFrameLayout, false)
                showEditText = getBoolean(R.styleable.ToolbarLayout_showEditText, false)
                showRightText = getBoolean(R.styleable.ToolbarLayout_showRightText, false)
                showRightFirstIcon = getBoolean(R.styleable.ToolbarLayout_showRightFirstIcon, false)
                showRightSecondIcon =
                    getBoolean(R.styleable.ToolbarLayout_showRightSecondIcon, false)
                showLine = getBoolean(R.styleable.ToolbarLayout_showLine, false)
                leftText = getString(R.styleable.ToolbarLayout_leftText)
                rightText = getString(R.styleable.ToolbarLayout_rightText)
                recycle()
            }

        }
    }

    fun setBackClickListener(f: () -> Unit) {
        toolbarBack?.setOnClickListener { f() }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        toolbarBack?.apply {
            visibility(showBack)
        }
        toolbarLeftText?.apply {
            visibility(showLeftText)
            if (showLeftText) {
                text = leftText
                setTextColor(leftTextColor)
            }
        }
        toolbarRightText?.apply {
            visibility(showRightText)
            if (showRightText) {
                text = rightText
                setTextColor(rightTextColor)
            }
        }
    }
}