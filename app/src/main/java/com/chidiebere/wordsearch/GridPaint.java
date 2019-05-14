package com.chidiebere.wordsearch;

import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.LocaleList;

import java.io.Serializable;
import java.util.Locale;

public class GridPaint extends Paint implements Serializable {
    public GridPaint() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public GridPaint(int flags) {
        super(flags);
    }

    public GridPaint(Paint paint) {
        super(paint);
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void set(Paint src) {
        super.set(src);
    }

    @Override
    public int getFlags() {
        return super.getFlags();
    }

    @Override
    public void setFlags(int flags) {
        super.setFlags(flags);
    }

    @Override
    public int getHinting() {
        return super.getHinting();
    }

    @Override
    public void setHinting(int mode) {
        super.setHinting(mode);
    }

    @Override
    public void setAntiAlias(boolean aa) {
        super.setAntiAlias(aa);
    }

    @Override
    public void setDither(boolean dither) {
        super.setDither(dither);
    }

    @Override
    public void setLinearText(boolean linearText) {
        super.setLinearText(linearText);
    }

    @Override
    public void setSubpixelText(boolean subpixelText) {
        super.setSubpixelText(subpixelText);
    }

    @Override
    public void setUnderlineText(boolean underlineText) {
        super.setUnderlineText(underlineText);
    }

    @Override
    public void setStrikeThruText(boolean strikeThruText) {
        super.setStrikeThruText(strikeThruText);
    }

    @Override
    public void setFakeBoldText(boolean fakeBoldText) {
        super.setFakeBoldText(fakeBoldText);
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        super.setFilterBitmap(filter);
    }

    @Override
    public Style getStyle() {
        return super.getStyle();
    }

    @Override
    public void setStyle(Style style) {
        super.setStyle(style);
    }

    @Override
    public int getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(int color) {
        super.setColor(color);
    }

    @Override
    public int getAlpha() {
        return super.getAlpha();
    }

    @Override
    public void setAlpha(int a) {
        super.setAlpha(a);
    }

    @Override
    public void setARGB(int a, int r, int g, int b) {
        super.setARGB(a, r, g, b);
    }

    @Override
    public float getStrokeWidth() {
        return super.getStrokeWidth();
    }

    @Override
    public void setStrokeWidth(float width) {
        super.setStrokeWidth(width);
    }

    @Override
    public float getStrokeMiter() {
        return super.getStrokeMiter();
    }

    @Override
    public void setStrokeMiter(float miter) {
        super.setStrokeMiter(miter);
    }

    @Override
    public Cap getStrokeCap() {
        return super.getStrokeCap();
    }

    @Override
    public void setStrokeCap(Cap cap) {
        super.setStrokeCap(cap);
    }

    @Override
    public Join getStrokeJoin() {
        return super.getStrokeJoin();
    }

    @Override
    public void setStrokeJoin(Join join) {
        super.setStrokeJoin(join);
    }

    @Override
    public boolean getFillPath(Path src, Path dst) {
        return super.getFillPath(src, dst);
    }

    @Override
    public Shader getShader() {
        return super.getShader();
    }

    @Override
    public Shader setShader(Shader shader) {
        return super.setShader(shader);
    }

    @Override
    public ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    @Override
    public ColorFilter setColorFilter(ColorFilter filter) {
        return super.setColorFilter(filter);
    }

    @Override
    public Xfermode getXfermode() {
        return super.getXfermode();
    }

    @Override
    public Xfermode setXfermode(Xfermode xfermode) {
        return super.setXfermode(xfermode);
    }

    @Override
    public PathEffect getPathEffect() {
        return super.getPathEffect();
    }

    @Override
    public PathEffect setPathEffect(PathEffect effect) {
        return super.setPathEffect(effect);
    }

    @Override
    public MaskFilter getMaskFilter() {
        return super.getMaskFilter();
    }

    @Override
    public MaskFilter setMaskFilter(MaskFilter maskfilter) {
        return super.setMaskFilter(maskfilter);
    }

    @Override
    public Typeface getTypeface() {
        return super.getTypeface();
    }

    @Override
    public Typeface setTypeface(Typeface typeface) {
        return super.setTypeface(typeface);
    }

    @Override
    public void setShadowLayer(float radius, float dx, float dy, int shadowColor) {
        super.setShadowLayer(radius, dx, dy, shadowColor);
    }

    @Override
    public void clearShadowLayer() {
        super.clearShadowLayer();
    }

    @Override
    public Align getTextAlign() {
        return super.getTextAlign();
    }

    @Override
    public void setTextAlign(Align align) {
        super.setTextAlign(align);
    }


    @Override
    public Locale getTextLocale() {
        return super.getTextLocale();
    }

    @Override
    public LocaleList getTextLocales() {
        return super.getTextLocales();
    }

    @Override
    public void setTextLocale( Locale locale) {
        super.setTextLocale(locale);
    }

    @Override
    public void setTextLocales(LocaleList locales) {
        super.setTextLocales(locales);
    }

    @Override
    public boolean isElegantTextHeight() {
        return super.isElegantTextHeight();
    }

    @Override
    public void setElegantTextHeight(boolean elegant) {
        super.setElegantTextHeight(elegant);
    }

    @Override
    public float getTextSize() {
        return super.getTextSize();
    }

    @Override
    public void setTextSize(float textSize) {
        super.setTextSize(textSize);
    }

    @Override
    public float getTextScaleX() {
        return super.getTextScaleX();
    }

    @Override
    public void setTextScaleX(float scaleX) {
        super.setTextScaleX(scaleX);
    }

    @Override
    public float getTextSkewX() {
        return super.getTextSkewX();
    }

    @Override
    public void setTextSkewX(float skewX) {
        super.setTextSkewX(skewX);
    }

    @Override
    public float getLetterSpacing() {
        return super.getLetterSpacing();
    }

    @Override
    public void setLetterSpacing(float letterSpacing) {
        super.setLetterSpacing(letterSpacing);
    }

    @Override
    public String getFontFeatureSettings() {
        return super.getFontFeatureSettings();
    }

    @Override
    public void setFontFeatureSettings(String settings) {
        super.setFontFeatureSettings(settings);
    }

    @Override
    public String getFontVariationSettings() {
        return super.getFontVariationSettings();
    }

    @Override
    public boolean setFontVariationSettings(String fontVariationSettings) {
        return super.setFontVariationSettings(fontVariationSettings);
    }

    @Override
    public float ascent() {
        return super.ascent();
    }

    @Override
    public float descent() {
        return super.descent();
    }

    @Override
    public float getFontMetrics(FontMetrics metrics) {
        return super.getFontMetrics(metrics);
    }

    @Override
    public FontMetrics getFontMetrics() {
        return super.getFontMetrics();
    }

    @Override
    public int getFontMetricsInt(FontMetricsInt fmi) {
        return super.getFontMetricsInt(fmi);
    }

    @Override
    public FontMetricsInt getFontMetricsInt() {
        return super.getFontMetricsInt();
    }

    @Override
    public float getFontSpacing() {
        return super.getFontSpacing();
    }

    @Override
    public float measureText(char[] text, int index, int count) {
        return super.measureText(text, index, count);
    }

    @Override
    public float measureText(String text, int start, int end) {
        return super.measureText(text, start, end);
    }

    @Override
    public float measureText(String text) {
        return super.measureText(text);
    }

    @Override
    public float measureText(CharSequence text, int start, int end) {
        return super.measureText(text, start, end);
    }

    @Override
    public int breakText(char[] text, int index, int count, float maxWidth, float[] measuredWidth) {
        return super.breakText(text, index, count, maxWidth, measuredWidth);
    }

    @Override
    public int breakText(CharSequence text, int start, int end, boolean measureForwards, float maxWidth, float[] measuredWidth) {
        return super.breakText(text, start, end, measureForwards, maxWidth, measuredWidth);
    }

    @Override
    public int breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth) {
        return super.breakText(text, measureForwards, maxWidth, measuredWidth);
    }

    @Override
    public int getTextWidths(char[] text, int index, int count, float[] widths) {
        return super.getTextWidths(text, index, count, widths);
    }

    @Override
    public int getTextWidths(CharSequence text, int start, int end, float[] widths) {
        return super.getTextWidths(text, start, end, widths);
    }

    @Override
    public int getTextWidths(String text, int start, int end, float[] widths) {
        return super.getTextWidths(text, start, end, widths);
    }

    @Override
    public int getTextWidths(String text, float[] widths) {
        return super.getTextWidths(text, widths);
    }

    @Override
    public void getTextPath(char[] text, int index, int count, float x, float y, Path path) {
        super.getTextPath(text, index, count, x, y, path);
    }

    @Override
    public void getTextPath(String text, int start, int end, float x, float y, Path path) {
        super.getTextPath(text, start, end, x, y, path);
    }

    @Override
    public void getTextBounds(String text, int start, int end, Rect bounds) {
        super.getTextBounds(text, start, end, bounds);
    }

    @Override
    public void getTextBounds(char[] text, int index, int count, Rect bounds) {
        super.getTextBounds(text, index, count, bounds);
    }

    @Override
    public boolean hasGlyph(String string) {
        return super.hasGlyph(string);
    }

    @Override
    public float getRunAdvance(char[] text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset) {
        return super.getRunAdvance(text, start, end, contextStart, contextEnd, isRtl, offset);
    }

    @Override
    public float getRunAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset) {
        return super.getRunAdvance(text, start, end, contextStart, contextEnd, isRtl, offset);
    }

    @Override
    public int getOffsetForAdvance(char[] text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance) {
        return super.getOffsetForAdvance(text, start, end, contextStart, contextEnd, isRtl, advance);
    }

    @Override
    public int getOffsetForAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance) {
        return super.getOffsetForAdvance(text, start, end, contextStart, contextEnd, isRtl, advance);
    }

    @Override
    public boolean equalsForTextMeasurement( Paint other) {
        return super.equalsForTextMeasurement(other);
    }
}
