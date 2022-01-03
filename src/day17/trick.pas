program Trick;

const
  X1 = 143;
  X2 = 177;  
  Y1 = -106;
  Y2 = -71;

var
  I, J, K, S, Last, Count, Hits, Best: Integer;

function Abs(I: Integer): Integer;
begin
  if I >= 0 then Abs := I else Abs := -I;
end;

procedure Line(X, Y, Length, Color: Integer);
var
  I: Integer;
begin
  for I := 0 to Length - 1 do
    SetPixel(X + I, Y, Color);
end;

function Shoot(DX: Integer; DY: Integer): Integer;
var
  Step, X, Y, High: Integer;
begin
  Step := 0;
  X := 0;
  Y := 0;
  High := -32768;

  while True do
  begin
    Step := Step + 1;

    X := X + DX;
    Y := Y + DY;

    if Y > High then High := Y;

    if DX > 0 then DX := DX - 1 else if DX < 0 then DX := DX + 1;
    DY := DY - 1;

    if Y <= Y2 then
    begin
      if (DY < 0) and (Y < Y1) then
      begin
        if X < X1 then
          Shoot := X1 - X               (* Too short    *)
        else if X > X2 then
          Shoot := X - X2               (* Too long     *)
        else
          Shoot := Y1 - Y;              (* Fell through *)
        Exit;
      end;
            
      if (X1 <= X) and (X <= X2) and (Y1 <= Y) and (Y <= Y2) then
      begin
        if High > Best then
        begin
          Best := High;
          GotoXY(28, 16);
          WriteLn(Best);    
        end;
        Shoot := 0;
        Exit;
      end;
    end;
  end;
end;

procedure Setup;
begin
  TextColor(0);
  TextBackground(182);
  ClrScr;

  TextBackground(0);
  TextColor(255);

  Write('AoC 2021.17 Trick Shot          ');

  for I := 0 to 7 do
  begin
    Line(208 + I, 7 - I, 8, 224);
    Line(216 + I, 7 - I, 8, 252);
    Line(224 + I, 7 - I, 8, 28);
    Line(232 + I, 7 - I, 8, 31);
  end;

  TextColor(255);
  TextBackground(0);

  GotoXY(28, 5);  WriteLn("x-vel");    
  GotoXY(28, 10); WriteLn("y-vel");    
  GotoXY(28, 15); WriteLn("y-max");    
  GotoXY(28, 20); WriteLn("Hits ");

  TextColor(0);
  TextBackground(255);

  GotoXY(28, 6);  WriteLn(" 0   ");    
  GotoXY(28, 11); WriteLn(" 0   ");    
  GotoXY(28, 16); WriteLn(" 0   ");    
  GotoXY(28, 21); WriteLn(" 0   ");
end;

begin
  Setup;

  Last := -1;
  Best := -32768;
  Hits := 0;

  for I := 0 to 177 do
  begin
    GotoXY(28, 6);
    Write(I, ' ');

    for J := -106 to 106 do
    begin
      GotoXY(28, 11);
      Write(J, ' ');

      S := Shoot(I, J);
      if S = 0 then
      begin
        SetPixel(106 + J, I + 14, 255);
        Last := -1;
        Hits := Hits + 1;
        GotoXY(28, 21);
        Write(Hits);
      end
      else
      begin
        SetPixel(106 + J, I + 14, S);
        if (J > 0) then
        begin
          if S = Last then
          begin
            Count := Count + 1;
            if Count > 5 then           (* Nothing to see, please move along. *)
            begin
              Line(107 + J, I + 14, 106 - J, S);
              Break;
            end;
          end
          else
          begin
            Last := S;
            Count := 1;
          end;
        end;
      end;
    end;
  end;

  TextColor(0);
  TextBackground(182);
end.