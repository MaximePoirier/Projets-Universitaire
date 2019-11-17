var a,b : integer;
var t : array [5] of integer;
var Books : record
    id : integer;
    idf : integer
end
begin
    a:=0;
    b:=4;
    Books.id:=-30;
    Books.idf:=50;
    write(Books.id);
    write(Books.idf);
    write(-Books.idf*Books.id);
    if (a<b) then
                begin
                while(a<3) do
                        begin
                        t[a]:=a;
                        a:=a+1
                        end;
                end
    else
       begin
       a:=b*2;
       write(a)
       end;
    write(t[0]);
    write(t[1]);
    write(t[2]);
    colorLed(240,0,240)
end.

