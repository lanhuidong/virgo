<@c.html title="首页-记账本">
    <div class="text-center"><h1 class="text-info">版本1.1.0已完成，版本1.1.1开发中……</h1>
    <iframe width="600px" height="525px" frameborder="0" noresize="noresize" src="javascript:'
    <!doctype html>\n
    <html>\n\t
    <head>\n\t\t<title>JS1k, 1k demo submission [1022]</title>\n\t\t
        <meta charset=&quot;utf-8&quot;/>\n\t
    </head>\n\t
    <body>\n\t\t
        <canvas id=&quot;c&quot;></canvas>\n\t\t
        <script>\n\t\t\t
            var b = document.body;\n\t\t\t
            var c = document.getElementsByTagName(\'canvas\')[0];\n\t\t\t
            var a = c.getContext(\'2d\');\n\t\t\t
            document.body.clientWidth; // fix bug in webkit: http://qfox.nl/weblog/218\n\t\t
        </script>\n\t\t
        <script>\n// start of submission //\nwith(m=Math)
        C=cos,S=sin,P=pow,R=random;c.width=c.height=f=500;h=-250;
        function p(a,b,c){if(c>60)return[S(a*7)*(13+5/(.2+P(b*4,4)))-S(b)*50,b*f+50,625+C(a*7)*(13+5/(.2+P(b*4,4)))+b*400,a*1-b/2,a];A=a*2-1;B=b*2-1;if(A*A+B*B<1){if(c>37){n=(j=c&1)%3F6:4;o=.5/(a+.01)+C(b*125)*3-a*300;w=b*h;return[o*C(n)+w*S(n)+j*610-390,o*S(n)-w*C(n)+550-j*350,1180+C(B+A)*99-j*300,.4-a*.1+P(1-B*B,-h*6)*.15-a*b*.4+C(a+b)/5+P(C((o*(a+1)+(B>0%3Fw:-w))/25),30)*.1*(1-B*B),o/1e3+.7-o*w*3e-6]}if(c>32){c=c*1.16-.15;o=a*45-20;w=b*b*h;z=o*S(c)+w*C(c)+620;return[o*C(c)-w*S(c),28+C(B*.5)*99-b*b*b*60-z/2-h,z,(b*b*.3+P((1-(A*A)),7)*.15+.3)*b,b*.7]}o=A*(2-b)*(80-c*2);w=99-C(A)*120-C(b)*(-h-c*4.9)+C(P(1-b,7))*50+c*2;z=o*S(c)+w*C(c)+700;return[o*C(c)-w*S(c),B*99-C(P(b, 7))*50-c/3-z/1.35+450,z,(1-b/1.2)*.9+a*.1, P((1-b),20)/4+.05]}}setInterval(\'for(i=0;i<1e4;i++)if(s=p(R(),R(),i%2546/.74)){z=s[2];x=~~(s[0]*f/z-h);y=~~(s[1]*f/z-h);if(!m[q=y*f+x]|m[q]>z)m[q]=z,a.fillStyle=&quot;rgb(&quot;+~(s[3]*h)+&quot;,&quot;+~(s[4]*h)+&quot;,&quot;+~(s[3]*s[3]*-80)+&quot;)&quot;,a.fillRect(x,y,1,1)}\',0)\n// end of submission //\n\t\t
        </script>\n\t
    </body>\n
    </html>'">
    </iframe>
    </div>
    <script type="text/javascript">
    $(function(){
        $('header').find('li').removeClass('active');
        $('header').find('li:eq(0)').addClass('active');
        $('#sign-btn').click(signup);
        $('#login-form').submit(login);
        $('#regusername').blur(validateUsername);
        $('#regpassword').blur(validatePassword);
    });
    </script>
</@c.html>