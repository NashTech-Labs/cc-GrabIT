package com.knoldus.utils.email

//scalastyle:off
object EmailTemplate {

  val addUserSubject = "Welcome To GrabIT"
  def addUserMessage(name: String, email: String, password: String): String = s"""
      <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
      <html xmlns="http://www.w3.org/1999/xhtml">
          <head>
              <meta name="viewport" content="width=device-width" />
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
              <title>GrabIT</title>

          </head>

          <body style="font-size:12px; padding-top=0px;" align="left">
          <table class="main" style="max-width: 100%; margin: 0 auto; display: block; padding: 0px; text-align:left; border: #aaa solid 1px;"  cellpadding="0" cellspacing="0">
              <tr>
                  <td style="padding: 20px;">
                      <table  cellpadding="0" cellspacing="0">
                          <tr>
                              <td>
                                 <h3>Hi $name  </h3>
                              </td>
                            </tr>
                            <tr>
                                <td>
                                    <h3>Welcome To GrabIT! Lets Live Better</h3>
                                    <h4>We care for you and want you to live better</h4>
                               </td>
                            </tr>
                            <tr>
                                <td>
                                    You can access the GrabIT account using given username and password!<br/><br/>
                                    Username: $email <br/>
                                    Password: $password <br/><br/>
                                   <br> Regards<br>
                                    GrabIT Team
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table width="100%" align="left" style="border: #aaa solid 1px">
                <tr>
                    <td>
                        <address style="font-style:normal;padding-left: 18px;">
                            <strong><span style="color:#4078C0;">GrabIT,</span></strong><br/>
                            SDF L-11, Noida Special Economic Zone<br/>
                            Sector 81<br/>
                            Noida, Uttar Pradesh 201305<br/>
                            India<br/>
                            <abbr title="Phone">P:</abbr> +91 - 1142316525
                        </address>
                    </td>

                    <td>
                        Cheers,</br>
                        GrabIT Team
                        <br>
                   </td>
                </tr>
                <tr>
                </tr>
            </table>
            </body>
        </html>
      """.stripMargin

}
//scalastyle:off