<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="jquery.countdown.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
      $(function(){
		$('#counter').countdown({
          image: 'images/digits1.png',
          digitImages: 3,
          digitWidth: 53,
          digitHeight: 77,
          startTime: '10',
          timerEnd: function(){ },
          format: 'ss'
        });
      });
    </script>
    <style type="text/css">
      br { clear: both; }
      .cntSeparator {
        font-size: 54px;
        margin: 10px 7px;
        color: #000;
      }
      .desc { margin: 7px 3px; }
      .desc div {
        float: left;
        font-family: Arial;
        width: 70px;
        margin-right: 65px;
        font-size: 13px;
        font-weight: bold;
        color: #000;
      }
    </style>
  </head>
<body>
  <div id="counter"></div>
  <div class="desc">
    <div>Minutos</div>
    <div>Segundos</div>
  </div>
</body>
</html>