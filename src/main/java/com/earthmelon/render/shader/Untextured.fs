#version 450 core

out vec4 out_Colour;

uniform vec4 tintColour;

void main(){
//         out_Colour = vec4(tintColour.r, tintColour.g, tintColour.b, tintColour.a);
        out_Colour = vec4(tintColour.r, tintColour.g, tintColour.b, tintColour.a);
}