<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main">
	 <title>Publish/Subscribe Example</title>
</head>
<body>
<g:javascript src="jquery.form.js"/>
<g:javascript src="jquery.atmosphere.js"/>
<h1>PubSub Sample using Grails Atmosphere PlugIn. By default the sample use the RedisBroadcaster.</h1>
<br/>
<h2>(Mock Login) Input user name, available users (tim/andy)</h2>
<br/>
<div id='pubsub'>
    <input id='topic' type='text'/>
</div>

<br/>
<h2>Select transport to use for subscribing,You can change the transport any time.</h2>

<div id='select_transport'>
    <select id="transport">
        <option id="autodetect" value="websocket">autodetect</option>
        <option id="websocket" value="websocket">websocket</option>
        <option id="sse" value="sse">sse</option>
        <option id="jsonp" value="jsonp">jsonp</option>
        <option id="long-polling" value="long-polling">long-polling</option>
        <option id="streaming" value="streaming">http streaming</option>
    </select>
    <input id='connect' class='button' type='submit' name='connect' value='Connect'/>
</div>
<br/>
<br/>

<h2 id="s_h" class='hidden'>Publish Topic</h2>

<div id='sendMessage' class='hidden'>
    <input id='phrase' type='text'/>
    <input id='send_message' class='button' type='submit' name='Publish' value='Publish Message'/>
</div>
<br/>

<h2>Real Time PubSub Update</h2>
<br/>
<ul></ul>
</body>
</html>
