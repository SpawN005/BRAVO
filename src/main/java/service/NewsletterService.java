package service;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class NewsletterService {
    private final String SMTP_SERVER = "smtp.gmail.com";
    private final String USERNAME = "tasnim ben hamouda";
    private final String PASSWORD = "223JFT1894";
    private final String EMAIL_FROM = "tasnim.benhamouda@esprit.tn";
    private final String EMAIL_SUBJECT = "A new blog is added to the plateform!";

    public void sendEmail(String emailTo) {
        String template = "<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                "<head>\n" +
                "<title></title>\n" +
                "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n" +
                "<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                "<!--[if !mso]><!-->\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Merriweather\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "<!--<![endif]-->\n" +
                "<style>\n" +
                "\t\t* {\n" +
                "\t\t\tbox-sizing: border-box;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tbody {\n" +
                "\t\t\tmargin: 0;\n" +
                "\t\t\tpadding: 0;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ta[x-apple-data-detectors] {\n" +
                "\t\t\tcolor: inherit !important;\n" +
                "\t\t\ttext-decoration: inherit !important;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t#MessageViewBody a {\n" +
                "\t\t\tcolor: inherit;\n" +
                "\t\t\ttext-decoration: none;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tp {\n" +
                "\t\t\tline-height: inherit\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.desktop_hide,\n" +
                "\t\t.desktop_hide table {\n" +
                "\t\t\tmso-hide: all;\n" +
                "\t\t\tdisplay: none;\n" +
                "\t\t\tmax-height: 0px;\n" +
                "\t\t\toverflow: hidden;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.image_block img+div {\n" +
                "\t\t\tdisplay: none;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.menu_block.desktop_hide .menu-links span {\n" +
                "\t\t\tmso-hide: all;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t@media (max-width:660px) {\n" +
                "\n" +
                "\t\t\t.desktop_hide table.icons-inner,\n" +
                "\t\t\t.social_block.desktop_hide .social-table {\n" +
                "\t\t\t\tdisplay: inline-block !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.icons-inner {\n" +
                "\t\t\t\ttext-align: center;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.icons-inner td {\n" +
                "\t\t\t\tmargin: 0 auto;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.image_block img.big,\n" +
                "\t\t\t.row-content {\n" +
                "\t\t\t\twidth: 100% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.menu-checkbox[type=checkbox]~.menu-links {\n" +
                "\t\t\t\tdisplay: none !important;\n" +
                "\t\t\t\tpadding: 5px 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.menu-checkbox[type=checkbox]:checked~.menu-trigger .menu-open,\n" +
                "\t\t\t.menu-checkbox[type=checkbox]~.menu-links span.sep {\n" +
                "\t\t\t\tdisplay: none !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.menu-checkbox[type=checkbox]:checked~.menu-links,\n" +
                "\t\t\t.menu-checkbox[type=checkbox]~.menu-trigger {\n" +
                "\t\t\t\tdisplay: block !important;\n" +
                "\t\t\t\tmax-width: none !important;\n" +
                "\t\t\t\tmax-height: none !important;\n" +
                "\t\t\t\tfont-size: inherit !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.menu-checkbox[type=checkbox]~.menu-links>a,\n" +
                "\t\t\t.menu-checkbox[type=checkbox]~.menu-links>span.label {\n" +
                "\t\t\t\tdisplay: block !important;\n" +
                "\t\t\t\ttext-align: center;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.menu-checkbox[type=checkbox]:checked~.menu-trigger .menu-close {\n" +
                "\t\t\t\tdisplay: block !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.mobile_hide {\n" +
                "\t\t\t\tdisplay: none;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.stack .column {\n" +
                "\t\t\t\twidth: 100%;\n" +
                "\t\t\t\tdisplay: block;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.mobile_hide {\n" +
                "\t\t\t\tmin-height: 0;\n" +
                "\t\t\t\tmax-height: 0;\n" +
                "\t\t\t\tmax-width: 0;\n" +
                "\t\t\t\toverflow: hidden;\n" +
                "\t\t\t\tfont-size: 0px;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.desktop_hide,\n" +
                "\t\t\t.desktop_hide table {\n" +
                "\t\t\t\tdisplay: table !important;\n" +
                "\t\t\t\tmax-height: none !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.reverse {\n" +
                "\t\t\t\tdisplay: table;\n" +
                "\t\t\t\twidth: 100%;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.reverse .column.first {\n" +
                "\t\t\t\tdisplay: table-footer-group !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.reverse .column.last {\n" +
                "\t\t\t\tdisplay: table-header-group !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.row-4 td.column.first .border {\n" +
                "\t\t\t\tpadding: 15px 0 0;\n" +
                "\t\t\t\tborder-top: 0;\n" +
                "\t\t\t\tborder-right: 0px;\n" +
                "\t\t\t\tborder-bottom: 0;\n" +
                "\t\t\t\tborder-left: 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.row-4 td.column.last .border {\n" +
                "\t\t\t\tpadding: 0;\n" +
                "\t\t\t\tborder-top: 0;\n" +
                "\t\t\t\tborder-right: 0px;\n" +
                "\t\t\t\tborder-bottom: 0;\n" +
                "\t\t\t\tborder-left: 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.row-8 td.column.first .border,\n" +
                "\t\t\t.row-8 td.column.last .border {\n" +
                "\t\t\t\tpadding: 5px 0;\n" +
                "\t\t\t\tborder-top: 0;\n" +
                "\t\t\t\tborder-right: 0px;\n" +
                "\t\t\t\tborder-bottom: 0;\n" +
                "\t\t\t\tborder-left: 0;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t#memu-r0c0m2:checked~.menu-links {\n" +
                "\t\t\tbackground-color: transparent !important;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t#memu-r0c0m2:checked~.menu-links a,\n" +
                "\t\t#memu-r0c0m2:checked~.menu-links span {\n" +
                "\t\t\tcolor: #ffffff !important;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none; background-color: #ffffff;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; background-image: url('https://i.postimg.cc/sDq51xzn/Artboard-171.png'); background-position: top center; background-repeat: no-repeat; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 40px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:5px;padding-top:5px;width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"Your Logo\" src=\"https://i.imgur.com/iyXAUXi.png\" style=\"display: block; height: auto; border: 0; width: 128px; max-width: 100%;\" title=\"Your Logo\" width=\"128\"/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:40px;line-height:40px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"menu_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"color:#ffffff;font-family:inherit;font-size:15px;text-align:center;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"alignment\" style=\"text-align:center;font-size:0px;\">\n" +
                "<!--[if !mso]><!--><input class=\"menu-checkbox\" id=\"memu-r0c0m2\" style=\"display:none !important;max-height:0;visibility:hidden;\" type=\"checkbox\"/>\n" +
                "<!--<![endif]-->\n" +
                "<div class=\"menu-trigger\" style=\"display:none;max-height:0px;max-width:0px;font-size:0px;overflow:hidden;\"><label class=\"menu-label\" for=\"memu-r0c0m2\" style=\"height: 36px; width: 36px; display: inline-block; cursor: pointer; mso-hide: all; user-select: none; align: center; text-align: center; color: #ffffff; text-decoration: none; background-color: transparent; border-radius: 0;\"><span class=\"menu-open\" style=\"mso-hide:all;font-size:26px;line-height:36px;\">☰</span><span class=\"menu-close\" style=\"display:none;mso-hide:all;font-size:26px;line-height:36px;\">✕</span></label></div>\n" +
                "<div class=\"menu-links\">\n" +
                "<!--[if mso]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"\"><tr style=\"text-align:center;\"><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]--><a href=\"http://www.example.com\" style=\"mso-hide:false;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline-block;color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:15px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">ART WORK</a>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]--><a href=\"http://www.example.com\" style=\"mso-hide:false;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline-block;color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:15px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">Blog</a>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]--><a href=\"http://www.example.com\" style=\"mso-hide:false;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline-block;color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:15px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">EVENT</a>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px\"><![endif]--><span class=\"label\" style=\"mso-hide:false;padding-top:10px;padding-bottom:10px;padding-left:10px;padding-right:10px;display:inline;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:15px;color:false;letter-spacing:normal;\">DONATION</span>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]></tr></table><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:50px;line-height:50px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #e2d7c7; background-position: top center; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:50px;line-height:50px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:5px;padding-left:20px;padding-right:20px;padding-top:5px;text-align:center;width:100%;\">\n" +
                "<h1 style=\"margin: 0; color: #605d55; direction: ltr; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; font-size: 18px; font-weight: 700; letter-spacing: 6px; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0;\"><span class=\"tinyMce-placeholder\"><strong>VISIT</strong><br/></span></h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-left:20px;padding-right:20px;text-align:center;width:100%;\">\n" +
                "<h1 style=\"margin: 0; color: #605d55; direction: ltr; font-family: 'Merriwheater', 'Georgia', serif; font-size: 40px; font-weight: 400; letter-spacing: 3px; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0;\"><span class=\"tinyMce-placeholder\">tun'ART</span></h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:5px;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"25%\">\n" +
                "<tr>\n" +
                "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 3px solid #605D55;\"><span> </span></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:50px;line-height:50px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; background-repeat: no-repeat; color: #000000; background-image: url('https://i.postimg.cc/x1s8qFL9/Artboard-21.png'); background-position: center top; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr class=\"reverse\">\n" +
                "<td class=\"column column-1 first\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 15px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"41.666666666666664%\">\n" +
                "<div class=\"border\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">\n" +
                "<div style=\"font-family: sans-serif\">\n" +
                "<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 24px; color: #605d55; line-height: 2;\">\n" +
                "<p dir=\"ltr\" style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 32px;\"><span style=\"font-size:16px;\">\"You cant sit around and wait for somebody to say who you are. You need to write it and paint it and do it.\"</span></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td class=\"column column-2 last\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"58.333333333333336%\">\n" +
                "<div class=\"border\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "<div align=\"right\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"Woman In Front Of An Abstract Painting\" class=\"big\" src=\"https://i.postimg.cc/wxRMWVLB/Artboard-19.png\" style=\"display: block; height: auto; border: 0; width: 355px; max-width: 100%;\" title=\"Woman In Front Of An Abstract Painting\" width=\"355\"/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;width:100%;\">\n" +
                "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"Woman Leaning On An Easle\" src=\"https://i.postimg.cc/L6M9xMmy/Artboard-181.png\" style=\"display: block; height: auto; border: 0; width: 300px; max-width: 100%;\" title=\"Woman Leaning On An Easle\" width=\"300\"/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">\n" +
                "<div style=\"font-family: sans-serif\">\n" +
                "<div class=\"\" style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #605d55; line-height: 1.5;\">\n" +
                "<p dir=\"ltr\" style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 24px;\"><span style=\"font-size:16px;\">The Art of Painting: Can Abstract Art Be Decoded?</span></p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 24px;\"><span style=\"font-size:16px;\"><strong>What is Abstract Art?</strong> </span></p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 24px;\"><span style=\"font-size:16px;\"><strong>What Qualifies an Artwork to be Considered Abstract Art?</strong></span></p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 24px;\"><span style=\"font-size:16px;\"><strong>Understanding Art: Can Abstract Art be Decoded?</strong></span></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:5px;line-height:5px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:left;\">\n" +
                "<div align=\"left\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:119px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">SEE BLOG</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-8\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr class=\"reverse\">\n" +
                "<td class=\"column column-1 first\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"border\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"paragraph_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\">\n" +
                "<div style=\"color:#000000;direction:ltr;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:15px;font-weight:400;letter-spacing:0px;line-height:120%;text-align:left;mso-line-height-alt:18px;\">\n" +
                "<p dir=\"ltr\" style=\"margin: 0; margin-bottom: 16px;\"> </p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0; margin-bottom: 16px;\"> </p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0; margin-bottom: 16px;\">The art of photography.</p>\n" +
                "<p dir=\"ltr\" style=\"margin: 0;\"><strong>Chefchaouen: The Blue Pearl of Morocco</strong></p>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:5px;line-height:5px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:right;\">\n" +
                "<div align=\"right\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:119px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">SEE BLOG<br/></span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td class=\"column column-2 last\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"border\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-left:10px;padding-top:55px;width:100%;padding-right:0px;\">\n" +
                "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"Man In Front Of A Lighting Art Installation\" src=\"https://i.postimg.cc/wTFCDKMR/14643.jpg\" style=\"display: block; height: auto; border: 0; width: 310px; max-width: 100%;\" title=\"Man In Front Of A Lighting Art Installation\" width=\"310\"/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:220px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">DISCOVER MORE BLOGS</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-10\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:5px;padding-left:20px;padding-right:20px;padding-top:5px;text-align:center;width:100%;\">\n" +
                "<h1 style=\"margin: 0; color: #605d55; direction: ltr; font-family: 'Merriwheater', 'Georgia', serif; font-size: 24px; font-weight: 400; letter-spacing: 2px; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0;\"><span class=\"tinyMce-placeholder\">INTERESTED?<br/></span></h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-11\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:42px;width:97px;v-text-anchor:middle;\" arcsize=\"0%\" stroke=\"false\" fillcolor=\"#605d55\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#605d55;border-radius:0px;width:auto;border-top:0px solid #FFFFFF;font-weight:400;border-right:0px solid #FFFFFF;border-bottom:0px solid #FFFFFF;border-left:0px solid #FFFFFF;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:35px;padding-right:35px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">YES</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:97px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">MAYBE</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "<td class=\"column column-3\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:99px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:35px;padding-right:35px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">NO</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-12\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-13\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; background-repeat: no-repeat; color: #000000; background-position: top center; background-image: url('https://i.postimg.cc/xCXz0V7r/Artboard-23.png'); width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:20px;line-height:20px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-left:20px;padding-right:20px;text-align:center;width:100%;\">\n" +
                "<h1 style=\"margin: 0; color: #605d55; direction: ltr; font-family: 'Merriwheater', 'Georgia', serif; font-size: 22px; font-weight: 400; letter-spacing: 3px; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0;\"><strong><span class=\"tinyMce-placeholder\">THEN VISIT OUR PLATFORM</span></strong></h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:50px;line-height:50px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-14\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:88px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">BLOG</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:126px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">ART WORK<br/></span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-15\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:96px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">EVENT</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<div class=\"spacer_block\" style=\"height:10px;line-height:10px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:125px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">DONATION<br/></span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-16\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"button_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.example.com\" style=\"height:46px;width:99px;v-text-anchor:middle;\" arcsize=\"0%\" strokeweight=\"1.5pt\" strokecolor=\"#605D55\" fillcolor=\"#ffffff\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#605d55; font-family:Tahoma, sans-serif; font-size:16px\"><![endif]--><a href=\"http://www.example.com\" style=\"text-decoration:none;display:inline-block;color:#605d55;background-color:#ffffff;border-radius:0px;width:auto;border-top:2px solid #605D55;font-weight:400;border-right:2px solid #605D55;border-bottom:2px solid #605D55;border-left:2px solid #605D55;padding-top:5px;padding-bottom:5px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\" target=\"_blank\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span dir=\"ltr\" style=\"font-size: 12px; word-break: break-word; line-height: 2; mso-line-height-alt: 24px;\"><strong><span data-mce-style=\"font-size:16px;\" dir=\"ltr\" style=\"font-size:16px;\">ABOUT</span></strong></span></span></a>\n" +
                "<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:30px;line-height:30px;font-size:1px;\"> </div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-17\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: top center;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 10px; padding-top: 10px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<div class=\"spacer_block\" style=\"height:40px;line-height:40px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"Your Logo\" src=\"https://i.imgur.com/iyXAUXi.png\" style=\"display: block; height: auto; border: 0; width: 128px; max-width: 100%;\" title=\"Your Logo\" width=\"128\"/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"social_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\">\n" +
                "<div align=\"center\" class=\"alignment\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social-table\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;\" width=\"144px\">\n" +
                "<tr>\n" +
                "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img alt=\"Facebook\" height=\"32\" src=\"https://i.postimg.cc/1tnFrZym/2504792-removebg-preview.png\" style=\"display: block; height: auto; border: 0;\" title=\"facebook\" width=\"32\"/></a></td>\n" +
                "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.twitter.com/\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"https://i.postimg.cc/vmpTMxdy/pngtree-twitter-social-media-round-icon-png-image-6315985.png\" style=\"display: block; height: auto; border: 0;\" title=\"twitter\" width=\"32\"/></a></td>\n" +
                "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.linkedin.com/\" target=\"_blank\"><img alt=\"Linkedin\" height=\"32\" src=\"https://i.postimg.cc/XYrnJNLY/174857.png\" style=\"display: block; height: auto; border: 0;\" title=\"linkedin\" width=\"32\"/></a></td>\n" +
                "<td style=\"padding:0 2px 0 2px;\"><a href=\"https://www.instagram.com/\" target=\"_blank\"><img alt=\"Instagram\" height=\"32\" src=\"https://i.postimg.cc/CMrtKGQb/insta.png\" style=\"display: block; height: auto; border: 0;\" title=\"instagram\" width=\"32\"/></a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"menu_block block-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"color:#605d55;font-family:inherit;font-size:14px;text-align:center;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"alignment\" style=\"text-align:center;font-size:0px;\">\n" +
                "<div class=\"menu-links\">\n" +
                "<!--[if mso]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"\"><tr style=\"text-align:center;\"><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:5px;padding-right:5px;padding-bottom:5px;padding-left:5px\"><![endif]--><a href=\"www.example.com\" style=\"mso-hide:false;padding-top:5px;padding-bottom:5px;padding-left:5px;padding-right:5px;display:inline-block;color:#605d55;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:14px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">Terms & Condition</a>\n" +
                "<!--[if mso]></td><td><![endif]--><span class=\"sep\" style=\"font-size:14px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;color:#605d55;\">|</span>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:5px;padding-right:5px;padding-bottom:5px;padding-left:5px\"><![endif]--><a href=\"www.example.com\" style=\"mso-hide:false;padding-top:5px;padding-bottom:5px;padding-left:5px;padding-right:5px;display:inline-block;color:#605d55;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:14px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">Contact Us</a>\n" +
                "<!--[if mso]></td><td><![endif]--><span class=\"sep\" style=\"font-size:14px;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;color:#605d55;\">|</span>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]><td style=\"padding-top:5px;padding-right:5px;padding-bottom:5px;padding-left:5px\"><![endif]--><a href=\"www.example.com\" style=\"mso-hide:false;padding-top:5px;padding-bottom:5px;padding-left:5px;padding-right:5px;display:inline-block;color:#605d55;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:14px;text-decoration:none;letter-spacing:normal;\" target=\"_self\">Unsubscribe</a>\n" +
                "<!--[if mso]></td><![endif]-->\n" +
                "<!--[if mso]></tr></table><![endif]-->\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<div class=\"spacer_block\" style=\"height:40px;line-height:40px;font-size:1px;\"> </div>\n" +
                "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"paragraph_block block-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\">\n" +
                "<div style=\"color:#000000;direction:ltr;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;font-size:14px;font-weight:400;letter-spacing:0px;line-height:120%;text-align:center;mso-line-height-alt:16.8px;\">\n" +
                "<p style=\"margin: 0;\">Want to change how you receive these emails?<br/>You can <span style=\"color: #000000;\"><a href=\"https://www.imdb.com/gp/r.html?C=3VQXDH0PMUMG3&K=8CELETMIDBQU&M=urn:rtn:msg:20230206082211501c15fee6ff49988f691abf22c0p0na&R=PKX55W2Q2R8M&T=C&U=https%3A%2F%2Fwww.imdb.com%2Fpreferences%2Femail%2F%3Fref_%3Dpe_64378520_697625930_eml_prefs&H=0RZOP79S7NADERUUAWVQ3OZMSMIA&ref_=pe_64378520_697625930_eml_prefs\" rel=\"noopener\" style=\"text-decoration: underline; color: #0068a5;\" target=\"_blank\">update your preferences</a></span> or <span style=\"color: #000000;\"><a href=\"https://www.imdb.com/gp/r.html?C=3VQXDH0PMUMG3&K=8CELETMIDBQU&M=urn:rtn:msg:20230206082211501c15fee6ff49988f691abf22c0p0na&R=Y1GJIO2ZHA08&T=X&U=https%3A%2F%2Fwww.imdb.com%2Fsubscriptions%2Fo%2F1Rp0bNH7PjHia0joPV0HQnxXEfjtJMj5cvXHfanYpLhY%3Fref_%3Deml_unsub&H=YOTEHAV1XFKGCMSMOJ4O5Y4HYNYA\" rel=\"noopener\" style=\"text-decoration: underline; color: #0068a5;\" target=\"_blank\">click here to unsubscribe</a>.</span></p>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-18\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px;\" width=\"640\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"pad\" style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "<tr>\n" +
                "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\">\n" +
                "<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                "<!--[if !vml]><!-->\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n" +
                "<!--<![endif]-->\n" +
                "<tr>\n" +
                "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"https://www.designedwithbee.com/\" style=\"text-decoration: none;\" target=\"_blank\"><img align=\"center\" alt=\"Designed with BEE\" class=\"icon\" height=\"32\" src=\"https://i.postimg.cc/fRn97PPZ/bee.png\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\" width=\"34\"/></a></td>\n" +
                "<td style=\"font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"https://www.designedwithbee.com/\" style=\"color: #9d9d9d; text-decoration: none;\" target=\"_blank\">Designed with BEE</a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "</html>";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(EMAIL_FROM, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );
            message.setSubject(EMAIL_SUBJECT);
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(template,"text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Le message a été envoyé avec succès!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}