var url = contextRoot+"/home/ajax";

function doIt()
{
alert(contextRoot);
    $.ajax({
        url: url,
        success: function(result)
        {
            alert(result);
        }
    });
}