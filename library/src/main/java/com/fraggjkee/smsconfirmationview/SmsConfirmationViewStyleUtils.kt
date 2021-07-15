package com.fraggjkee.smsconfirmationview

import android.content.Context
import android.util.AttributeSet

internal object SmsConfirmationViewStyleUtils {

    private var defaultStyle: SmsConfirmationView.Style? = null

    fun getDefault(context: Context): SmsConfirmationView.Style {
        if (defaultStyle == null) {
            val resources = context.resources
            val symbolViewStyle = SymbolView.Style(
                width = resources.getDimensionPixelSize(R.dimen.symbol_view_width),
                height = resources.getDimensionPixelSize(R.dimen.symbol_view_height),
                backgroundColor = context.getThemeColor(R.attr.colorSurface),
                borderColor = context.getThemeColor(R.attr.colorPrimary),
                bottomColor = context.getThemeColor(R.attr.colorPrimaryDark),
                borderWidth = resources.getDimensionPixelSize(R.dimen.symbol_view_stroke_width),
                bottomWidth = resources.getDimensionPixelSize(R.dimen.symbol_view_bottom_width),
                borderCornerRadius = resources.getDimension(R.dimen.symbol_view_corner_radius),
                textColor = context.getThemeColor(R.attr.colorOnSurface),
                textSize = resources.getDimensionPixelSize(R.dimen.symbol_view_text_size)
            )
            defaultStyle = SmsConfirmationView.Style(
                codeLength = SmsConfirmationView.DEFAULT_CODE_LENGTH,
                symbolsSpacing = resources.getDimensionPixelSize(R.dimen.symbols_spacing),
                symbolViewStyle = symbolViewStyle,
            )
        }
        return defaultStyle!!
    }

    fun getFromAttributes(
        attrs: AttributeSet,
        context: Context
    ): SmsConfirmationView.Style {

        val defaultStyle = getDefault(context)
        val defaultSymbolStyle = defaultStyle.symbolViewStyle
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.SmsConfirmationView, 0, 0)
        return with(typedArray) {
            val symbolWidth = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolWidth,
                defaultSymbolStyle.width
            )
            val symbolHeight = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolHeight,
                defaultSymbolStyle.height
            )
            val symbolBackgroundColor = getColor(
                R.styleable.SmsConfirmationView_scv_symbolBackgroundColor,
                defaultSymbolStyle.backgroundColor
            )
            val symbolBorderColor = getColor(
                R.styleable.SmsConfirmationView_scv_symbolBorderColor,
                defaultSymbolStyle.borderColor
            )
            val symbolBottomColor = getColor(
                R.styleable.SmsConfirmationView_scv_symbolBottomColor,
                defaultSymbolStyle.bottomColor
            )
            val symbolBorderWidth = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolBorderWidth,
                defaultSymbolStyle.borderWidth
            )
            val symbolBottomWidth = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolBottomWidth,
                defaultSymbolStyle.bottomWidth
            )
            val symbolTextColor = getColor(
                R.styleable.SmsConfirmationView_scv_symbolTextColor,
                defaultSymbolStyle.textColor
            )
            val symbolTextSize = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolTextSize,
                defaultSymbolStyle.textSize
            )
            val cornerRadius = getDimension(
                R.styleable.SmsConfirmationView_scv_symbolBorderCornerRadius,
                defaultSymbolStyle.borderCornerRadius
            )
            val codeLength = getInt(
                R.styleable.SmsConfirmationView_scv_codeLength,
                defaultStyle.codeLength
            )
            val symbolsSpacingPx = getDimensionPixelSize(
                R.styleable.SmsConfirmationView_scv_symbolsSpacing,
                defaultStyle.symbolsSpacing
            )
            val smsDetectionMode = getInt(
                R.styleable.SmsConfirmationView_scv_smsDetectionMode,
                SmsConfirmationView.SmsDetectionMode.AUTO.ordinal
            ).let { SmsConfirmationView.SmsDetectionMode.values()[it] }

            recycle()

            SmsConfirmationView.Style(
                codeLength = codeLength,
                symbolsSpacing = symbolsSpacingPx,
                symbolViewStyle = SymbolView.Style(
                    width = symbolWidth,
                    height = symbolHeight,
                    backgroundColor = symbolBackgroundColor,
                    borderColor = symbolBorderColor,
                    bottomColor = symbolBottomColor,
                    borderWidth = symbolBorderWidth,
                    bottomWidth = symbolBottomWidth,
                    borderCornerRadius = cornerRadius,
                    textColor = symbolTextColor,
                    textSize = symbolTextSize
                ),
                smsDetectionMode = smsDetectionMode
            )
        }
    }
}