<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="bootstrap-theme.css">
    <script src="bootstrap.js"></script>
    <script src="jquery.js"></script>
</head>

<body>
<script type="text/javascript">
    function progresstast1() {
        var timer1 = setInterval(function () {
            $.ajax({
                url: "/getprogress",
                type: "post",
                data: {forid:"urlid"} ,
                async: true,
                beforeSend: function () {

                },
                success: function (data) {
                    if (data != undefined) {
                        $("#progressid1").attr('style','width: '+data+'%;')
                    }
                }
            });
        }, 100);
    }

    function progresstast2() {
        var timer2 = setInterval(function () {
            $.ajax({
                url: "/getprogress",
                type: "post",
                data: {forid:"urlid2"} ,
                async: true,
                beforeSend: function () {

                },
                success: function (data) {
                    if (data != undefined) {
                        $("#progressid2").attr('style','width: '+data+'%;')
                    }
                }
            });
        }, 100);
    }

    function progresstast3() {
        var timer3 = setInterval(function () {
            $.ajax({
                url: "/getprogress",
                type: "post",
                data: {forid:"urlid3"} ,
                async: true,
                beforeSend: function () {

                },
                success: function (data) {
                    if (data != undefined) {
                        $("#progressid3").attr('style','width: '+data+'%;')
                    }
                }
            });
        }, 100);
    }

    function progresstast4() {
        var timer4 = setInterval(function () {
            $.ajax({
                url: "/getprogress",
                type: "post",
                data: {forid:"urlid4"} ,
                async: true,
                beforeSend: function () {

                },
                success: function (data) {
                    if (data != undefined) {
                        $("#progressid4").attr('style','width: '+data+'%;')
                    }
                }
            });
        }, 100);
    }

</script>


<script type="text/javascript">
    function task(urlc,pathc,forid) {
        $.ajax({
            url: "/justdodownload",
            type: "post",
            data: {urlc:urlc,pathc:pathc,forid:forid} ,
            async: true,
            beforeSend: function () {
                $("#progsstask").append('<div id='+forid+' class="alert alert-info">'+urlc+'<br><span class="label label-success">正在下载.....</span></div>')
            },
            success: function (data) {
                    $("#"+forid).fadeOut("slow");
                    $("#progsstask").append('<div id='+forid+' class="alert alert-info">'+urlc+'<br><span class="label label-primary">下载完成.....</span></div>')
            }
        });
    };


    $(document).ready(function () {
        $("#imabutton").click(function () {
            if ($("#urlid").val() != "" && $("#pathid").val() != "") {
                task($("#urlid").val(), $("#pathid").val(),"urlid")
                progresstast1();
            }
            if ($("#urlid2").val() != "" && $("#pathid2").val() != "") {
                task($("#urlid2").val(), $("#pathid2").val(),"urlid2")
                progresstast2();
            }
            if ($("#urlid3").val() != "" && $("#pathid3").val() != "") {
                task($("#urlid3").val(), $("#pathid3").val(),"urlid3")
                progresstast3();
            }
            if ($("#urlid4").val() != "" && $("#pathid4").val() != "") {
                task($("#urlid4").val(), $("#pathid4").val(),"urlid4")
                progresstast4();
            }

        });
    });
</script>

<div class="container" id="progsstask">
</div>

<div class="container">
    <div class="row clearfix">
        <h2>下载进度:</h2>
        <div id="progressid">
            <div class="progress progress-striped">
                <div id="progressid1" class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 0%;">
                </div>
            </div>
            <div class="progress progress-striped">
                <div id="progressid2"class="progress-bar progress-bar-info" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 0%;">
                </div>
            </div>
            <div class="progress progress-striped">
                <div id="progressid3"class="progress-bar progress-bar-warning" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 0%;">
                </div>
            </div>
            <div class="progress progress-striped">
                <div id="progressid4" class="progress-bar progress-bar-danger" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 0%;">
                </div>
            </div>

        </div>
    </div>
</div>


<div class="container">
    <div class="row clearfix">

        <form class="form-horizontal modal-content" role="form">
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">URL</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="urlid" placeholder="请输入下载地址">
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-sm-2 control-label">目录</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="pathid" placeholder="请输入保存目录" value="d:\0">
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">URL</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="urlid2" placeholder="请输入下载地址">
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-sm-2 control-label">目录</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="pathid2" placeholder="请输入保存目录" value="d:\0">
                </div>
            </div>

            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">URL</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="urlid3" placeholder="请输入下载地址">
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-sm-2 control-label">目录</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="pathid3" placeholder="请输入保存目录">
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">URL</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="urlid4" placeholder="请输入下载地址">
                </div>
            </div>

            <div class="form-group">
                <label for="lastname" class="col-sm-2 control-label">目录</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="pathid4" placeholder="请输入保存目录">
                </div>
            </div>

            <div class="form-group" style="text-align: right">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="imabutton" type="button" class="btn btn-default">提交</button>&nbsp;&nbsp;
                </div>
            </div>
        </form>

    </div>
</div>


</body>
</html>
