<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 2/4/18
  Time: 9:19 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LogIn Page</title>
    <meta name="layout" content="application">
</head>

<body>

<div class="container">

    <div class="col-lg-6">
        <div class="col-lg-12" style="margin-top: 25px">

            <div class=" panel panel-default     ">
                <div class="panel-heading " style="background:salmon">
                    Recent Shares
                </div>

                <div class="panel-body">
                    <div class="row" style="padding-bottom: 10px">
                        <div class="col-lg-2">
                            <img class="img-responsive" src="#" alt="himanshi">
                        </div>

                        <div class="col-lg-10">
                            <span class="text">Himanshi Gupta</span>
                            <span class="text-muted">@himanshi 10min</span>
                            <span class="text-primary" style="float: right">Grails</span>

                            <div class="text">
                                <p>
                                    Wikiversity Free course materials · Wikiquote Free quote compendium ·
                                    MediaWiki Free & open wiki application · Wikisource Free library ·
                                    Wikispecies Free species directory · Meta-Wiki Community coordination & documentation.
                                </p>
                            </div>

                            <div>
                                <i class="fab fa-facebook fa-2x"></i>
                                <i class="fab fa-tumblr fa-2x"></i>
                                <i class="fab fa-google-plus-g fa-2x"></i>
                                <a href="#" class="text-primary"
                                   style="text-decoration: underline;float: right">view post</a>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-2">
                            <img class="img-responsive" src="#" alt="himanshi">
                        </div>

                        <div class="col-lg-10">
                            <span class="text">Himanshi Gupta</span>
                            <span class="text-muted">@himanshi 10min</span>
                            <span class="text-primary" style="float: right">Grails</span>

                            <div class="text">
                                <p>
                                    Wikiversity Free course materials · Wikiquote Free quote compendium ·
                                    MediaWiki Free & open wiki application · Wikisource Free library ·
                                    Wikispecies Free species directory · Meta-Wiki Community coordination & documentation.
                                </p>
                            </div>

                            <div>
                                <i class="fab fa-facebook fa-2x"></i>
                                <i class="fab fa-tumblr fa-2x"></i>
                                <i class="fab fa-google-plus-g fa-2x"></i>
                                <a href="#" class="text-primary"
                                   style="text-decoration: underline;float: right">view post</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-12" style="margin-top: 25px">

            <div class=" panel panel-default     ">
                <div class="panel-heading" style="background: salmon">
                    <span class="text">Top Posts</span>
                    <span class="dropdown" style="float: right">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Today
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">1 Week</a></li>
                            <li><a href="#">1 Month</a></li>
                            <li><a href="#">1 Year</a></li>
                        </ul>
                    </span>
                </div>

                <div class="panel-body">
                    <ls:getTopPosts></ls:getTopPosts>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
            <div class="col-lg-12" style="margin-top: 25px">
                <div class=" panel panel-default">
                    <div class="panel-heading">
                        <g:render template="logInHandler"></g:render>
                    </div>
                </div>
            </div>
            <div class="col-lg-12" style="margin-top: 25px;">
            <div class=" panel panel-default ">
                <div class="panel-heading">
                    <g:render template="register"></g:render>
                </div>
            </div>
            </div>
    </div>
</div>

</body>
</html>
