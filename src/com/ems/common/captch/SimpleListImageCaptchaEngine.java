//
// Decompiled by Procyon v0.5.36
//

package com.ems.common.captch;

public class SimpleListImageCaptchaEngine //extends ListImageCaptchaEngine
{
//    protected void buildInitialFactories() {
//        final WordGenerator wordGenerator = (WordGenerator)new RandomWordGenerator("1234567890");
//        final SingleColorGenerator scg = new SingleColorGenerator(Color.blue);
//        final LineTextDecorator lineDecorator = new LineTextDecorator(Integer.valueOf(1), Color.blue);
//        final TextDecorator[] textdecorators = { (TextDecorator)lineDecorator };
//        final TextPaster textPaster = (TextPaster)new DecoratedRandomTextPaster(new Integer(30), new Integer(5), (ColorGenerator)scg, new TextDecorator[] { (TextDecorator)new BaffleTextDecorator(new Integer(1), Color.white) });
//        final BackgroundGenerator backgroundGenerator = (BackgroundGenerator)new GradientBackgroundGenerator(new Integer(220), new Integer(60), Color.white, Color.GRAY);
//        final Font[] fontsList = { new Font("Arial", 0, 10), new Font("Tahoma", 0, 10), new Font("Verdana", 0, 10) };
//        final FontGenerator fontGenerator = (FontGenerator)new RandomFontGenerator(new Integer(30), new Integer(35), fontsList);
//        final WordToImage wordToImage = (WordToImage)new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
//        this.addFactory((ImageCaptchaFactory)new GimpyFactory(wordGenerator, wordToImage));
//    }
}
