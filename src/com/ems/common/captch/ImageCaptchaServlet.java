//
// Decompiled by Procyon v0.5.36
//

package com.ems.common.captch;

import javax.servlet.http.HttpServlet;

public class ImageCaptchaServlet extends HttpServlet {
//    private Logger log;
//    private static final long serialVersionUID = 1L;
//
//    public ImageCaptchaServlet() {
//        this.log = Logger.getLogger((Class)this.getClass());
//    }
//
//    public void init(final ServletConfig servletConfig) throws ServletException {
//        super.init(servletConfig);
//    }
//
//    protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        byte[] captchaChallengeAsJpeg = null;
//        final ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//
//        try {
//            final String captchaId = httpServletRequest.getSession().getId();
//            final ImageCaptchaService imgcp = CaptchaServiceSingleton.getInstance().getImgcapa();
//            final BufferedImage challenge = imgcp.getImageChallengeForID(captchaId, httpServletRequest.getLocale());
////            final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder((OutputStream)jpegOutputStream);
////            jpegEncoder.encode(challenge);
//
//
//            JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
//
//            ImageOutputStream ios = ImageIO.createImageOutputStream(jpegOutputStream);
//            imageWriter.setOutput(ios);
//
//
//        }
//        catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(404);
//            return;
//        }
//        catch (CaptchaServiceException e2) {
//            httpServletResponse.sendError(500);
//            return;
//        }catch(Exception e) {
//        	e.printStackTrace();
//        }
//
//
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0L);
//        httpServletResponse.setContentType("image/jpeg");
//        final ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//        System.out.println("responseOutputStream.close");
//    }

}
