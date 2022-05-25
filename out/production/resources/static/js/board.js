// 게시판 페이지 로드
$(document).ready(function(){

    var replyid = $("#replyid").val();
    var replyid2 = $("#replyid2").val();

    $("#modify_div" + replyid).hide();

});

// 게시판 댓글 삭제 ajax
function reply_delete(a, b, i) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var idxid = $("#allreply").data("rm_id");

    var BoardreplyDto = {
        id : a,
        boardid : b
    }

    if(confirm("댓글을 정말 삭제하시겠습니까?")){

        $.ajax({

            url : "/replydel",
            data : BoardreplyDto,
            type : "POST",
            beforeSend : function(xhr)
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },

            success : function(result) {
                alert(result);

                $("#allreply" + a).remove();

            },
            error : function(result) {
                alert(result);
            }

        })

    }

}

// 댓글 수정
var ridx = -1;
function replymodify(i) {

    if(ridx != -1){
        alert('이미 수정 중인 댓글이 있습니다.')
        return;
    }

    if(confirm("해당 댓글을 수정하시겠습니까?")) {

        var test = document.getElementsByName("reply_con")[i].innerHTML;

        $("#modify_div #modify_text").eq(i).val(test);

        showReplyForm(true,i);

    }
}

function showReplyForm(f, i){
    if(f){
        ridx = i;
        document.getElementsByName("reply_div")[i].style.display = "none";
        document.getElementsByName("modify_div")[i].style.display = "block";
    }
    else {
        ridx = -1;
        document.getElementsByName("reply_div")[i].style.display = "block";
        document.getElementsByName("modify_div")[i].style.display = "none";
    }
}

function modifyComplete(pk) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var text = $("#modify_div #modify_text").eq(ridx).val();

    var BoardreplyDto = {
        id : pk,
        replycontents : text
    }

    if(confirm("댓글을 정말 수정하시겠습니까?")){

        $.ajax({

            url : "/replymodify",
            data : BoardreplyDto,
            type : "POST",
            beforeSend : function(xhr)
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader(header, token);
                },

            success : function(data) {
                alert(data);

            },
            error : function(result) {
                alert(result);
            }

        })

    }

}