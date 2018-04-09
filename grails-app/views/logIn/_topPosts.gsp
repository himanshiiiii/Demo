<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-lg-2">
        <img class="img-responsive" src="#" alt="himanshi">
    </div>

    <div class="col-lg-10">
        <span class="text">${demo.createdBy.firstName}</span>
        <span class="text-muted">@"${demo.createdBy.userName}" 10min</span>
        <a href="${createLink(controller:'topic', ation:'index' ,id:demo.id)}" class="text-primary pull-right">"${demo.topicName}"</a>

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
            <a href="${createLink(controller: 'resource',action: 'index',id:demo.id)}" class="text-primary pull-right"
               style="text-decoration: underline">view post</a>
        </div>
    </div>
</div>
</body>
</html>